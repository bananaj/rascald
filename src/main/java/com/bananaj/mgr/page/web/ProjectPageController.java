package com.bananaj.mgr.page.web;

import com.bananaj.common.web.MgrController;
import com.bananaj.mgr.domain.*;
import com.bananaj.mgr.service.ExecutionService;
import com.bananaj.mgr.service.TestCaseService;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.util.*;

@Controller
@RequestMapping("/projectpage")
public class ProjectPageController extends MgrController {

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExecutionService executionService;

    @RequestMapping("/home/{id}")
    @ResponseBody
    public String home(@PathVariable String id, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("project", testCaseService.findProjectById(id));
        return renderer.renderTemplate("projectpage/home", context);
    }

    @RequestMapping("/testcaseadd/{projectId}")
    @ResponseBody
    public String testcaseadd(@PathVariable String projectId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("project", testCaseService.findProjectById(projectId));
        return renderer.renderTemplate("projectpage/testcaseform", context);
    }

    @RequestMapping("/testcase/{testCaseId}")
    @ResponseBody
    public String testcase(@PathVariable String testCaseId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();

        TestCase testCase = testCaseService.findTestCaseById(testCaseId);
        context.put("testCase", testCase);
        context.put("project", testCase.getProject());
        if (testCase.getInfos().size() > 0) {
            context.put("testCaseInfo", testCase.getInfos().iterator().next());
        }
        return renderer.renderTemplate("projectpage/testcase", context);
    }

    @RequestMapping("/testcase/{testCaseId}/edit")
    @ResponseBody
    public String testcaseedit(@PathVariable String testCaseId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();

        TestCase testCase = testCaseService.findTestCaseById(testCaseId);
        context.put("testCase", testCase);
        context.put("project", testCase.getProject());
        if (testCase.getInfos().size() > 0) {
            context.put("testCaseInfo", testCase.getInfos().iterator().next());
        }
        return renderer.renderTemplate("projectpage/testcaseform", context);
    }

    @RequestMapping("/testcasesave")
    public String testcasesave(@RequestParam String projectId, HttpSession session, HttpServletRequest request) {
        String testCaseId = request.getParameter("testCaseId");
        TestCase testCase = null;
        TestCaseInfo testCaseInfo = null;

        boolean isNew = false;

        if (testCaseId == null) {
            isNew = true;
            Project project = testCaseService.findProjectById(projectId);
            testCase = new TestCase();
            testCase.setProject(project);
            testCaseInfo = new TestCaseInfo();
            testCaseInfo.setTestCase(testCase);
        }
        else {
            testCase = testCaseService.findTestCaseById(testCaseId);
            testCaseInfo = testCase.getInfos().iterator().next();
        }

        testCase.setTitle(request.getParameter("title"));

        Set<ConstraintViolation<TestCase>> violations = validator.validate(testCase);
        testCaseInfo.setSummary(request.getParameter("summary"));
        testCaseInfo.setPreconditions(request.getParameter("preconditions"));

        if (testCaseId == null) {
            testCase.getInfos().add(testCaseInfo);
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        Set<Step> tempSteps = new HashSet<Step>();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (parameterName.startsWith("stepdescription")) {
                String stepDescription = request.getParameter(parameterName);
                String[] parts = parameterName.split("\\.");
                String stepExpected = request.getParameter("stepexpected." + parts[1]);
                String stepId = request.getParameter("stepid." + parts[1]);
                Step step = null;
                if (stepId == null || stepId.length() == 0) {
                    step = new Step();
                }
                else {
                    for(Step s: testCaseInfo.getSteps()) {
                        if (s != null && stepId.equals(s.getId())) {
                            step = s;
                            break;
                        }
                    }
                }
                step.setSortOrder(Integer.parseInt(request.getParameter("steporder." + parts[1])));
                step.setDescription(stepDescription);
                step.setExpected(stepExpected);
                step.setTestCaseInfo(testCaseInfo);
                tempSteps.add(step);

            }
        }

        List<Step> steps = new ArrayList<Step>();
        steps.addAll(tempSteps);
        Collections.sort(steps, new StepComparator());
        testCaseInfo.setSteps(steps);

        TestCase tc = testCaseService.saveTestCase(testCase);
        if (isNew) {
            userService.audit(userService.getUserByUserName(request.getUserPrincipal().getName()), "TC", tc.getId(), "CR" );
        }
        TestCaseInfo testCaseInfo2 = testCaseService.saveTestCaseInfo(testCaseInfo);
        return "redirect:home/" + projectId;
    }

    @RequestMapping("/testcase/{testCaseId}/execute/stub")
    @ResponseBody
    public String testcaseexecutestub( @PathVariable String testCaseId, HttpSession session, HttpServletRequest request)
    {
        Map<String, Object> context = renderer.getBaseContext();
        TestCase testCase = testCaseService.findTestCaseById(testCaseId);
        context.put("testCase", testCase);
        context.put("project", testCase.getProject());
        return renderer.renderTemplate("projectpage/testcaseexecutestub", context);
    }

    @RequestMapping("/testcaseexecutesave/{testCaseId}")
    public String testcaseexecutesave(@PathVariable String testCaseId, HttpSession session, HttpServletRequest request)
    {

        TestCase testCase = testCaseService.findTestCaseById(testCaseId);
        TestCaseInfo testCaseInfo = null;
        if (testCase.getInfos().size() > 0) {
            testCaseInfo = testCase.getInfos().iterator().next();
        }
        Execution execution = new Execution();
        execution.setTestCaseInfo(testCaseInfo);
        execution.setComments(request.getParameter("comments"));
        execution.setDate(new Date());
        execution.setUser(userService.getUserByUserName(request.getUserPrincipal().getName()));
        execution.setResultCode("F");
        execution.setStatusCode("C");
        executionService.saveExecution(execution);
        return "redirect:/page/projectpage/execution/" + execution.getId();
    }

    @RequestMapping("/execution/{executionId}")
    @ResponseBody
    public String execution(@PathVariable String executionId, HttpSession session, HttpServletRequest request) {

        Map<String, Object> context = renderer.getBaseContext();

        Execution execution = executionService.findExecutionById(executionId);
        context.put("execution", execution);
        context.put("testCaseInfo", execution.getTestCaseInfo());
        context.put("steps", execution.getTestCaseInfo().getSteps());
        context.put("testCase", execution.getTestCaseInfo().getTestCase());
        context.put("project", execution.getTestCaseInfo().getTestCase().getProject());
        context.put("steps", execution.getTestCaseInfo().getSteps());
        context.put("testCase", execution.getTestCaseInfo().getTestCase());
        context.put("project", execution.getTestCaseInfo().getTestCase().getProject());
        return renderer.renderTemplate("projectpage/execution", context);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("heading", "### Another deeper heading");
        return renderer.renderTemplate("projectpage/test", context);
    }
}