package com.bananaj.mgr.web.controller;

import com.bananaj.common.web.MgrController;
import com.bananaj.mgr.domain.*;
import com.bananaj.mgr.service.ExecutionService;
import com.bananaj.mgr.service.TestCaseService;
import com.bananaj.user.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
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
@RequestMapping("/testcase")
public class TestCaseController extends MgrController {

    @Autowired
    private UserService userService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private ExecutionService executionService;

    @RequestMapping("/project/{projectId}/testcases")
    @ResponseBody
    public String projecttestcases(@PathVariable String projectId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("testcases", testCaseService.findTestCasesForProjectId(projectId));
        return renderer.renderTemplate("testcase/projecttestcases", context);
    }

    @RequestMapping("/testcase/{testCaseId}/steps")
    @ResponseBody
    public String testcasesteps(@PathVariable String testCaseId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        TestCase testCase = testCaseService.findTestCaseById(testCaseId);
        if (testCase != null) {
            if (testCase.getInfos().size() > 0) {
                List<Step> steps = testCase.getInfos().iterator().next().getSteps();
                context.put("steps", steps);
            }
            else {
                context.put("steps", new ArrayList());
            }
        }
        return renderer.renderTemplate("testcase/testcasesteps", context);
    }


    @RequestMapping("/testcaseinfo/{testCaseInfoId}/executions")
    @ResponseBody
    public String testcaseinfoexecutions(@PathVariable String testCaseInfoId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("executions", executionService.findExecutionsForTestCaseInfoId(testCaseInfoId));
        return renderer.renderTemplate("testcase/testcaseinfoexecutions", context);
    }

    @RequestMapping("/testcase/{testCaseId}/executions")
    @ResponseBody
    public String testcaseexecutions(@PathVariable String testCaseId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("executions", executionService.findExecutionsForTestCaseId(testCaseId));
        return renderer.renderTemplate("testcase/testcaseinfoexecutions", context);
    }

    @RequestMapping("/testcasesave")
    @ResponseBody
    public String testcasesave(@RequestParam String projectId, HttpSession session, HttpServletRequest request) {
        String testCaseId = request.getParameter("testCaseId");
        TestCase testCase = null;
        TestCaseInfo testCaseInfo = null;

        boolean isNew = false;

        JSONObject validationFailures = new JSONObject();

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

        testCase.setTitle(request.getParameter("testcasetitle"));

        appendConstraintViolations(validationFailures, validator.validate(testCase), "", "");
        testCaseInfo.setSummary(request.getParameter("testcaseinfosummary"));
        testCaseInfo.setPreconditions(request.getParameter("testcaseinfopreconditions"));

        appendConstraintViolations(validationFailures, validator.validate(testCaseInfo), "", "");
        if (testCaseId == null) {
            testCase.getInfos().add(testCaseInfo);
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        Set<Step> tempSteps = new HashSet<Step>();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (parameterName.startsWith("stepdescription")) {
                String stepDescription = request.getParameter(parameterName);
                String index = parameterName.replace("stepdescription","");
                String stepExpected = request.getParameter("stepexpected" + index);
                String stepId = request.getParameter("stepid" + index);
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
                step.setSortOrder(Integer.parseInt(request.getParameter("steporder" + index)));
                step.setDescription(stepDescription);
                step.setExpected(stepExpected);
                step.setTestCaseInfo(testCaseInfo);
                appendConstraintViolations(validationFailures, validator.validate(step), "", index);
                tempSteps.add(step);

            }
        }

        List<Step> steps = new ArrayList<Step>();
        steps.addAll(tempSteps);
        Collections.sort(steps, new StepComparator());
        testCaseInfo.setSteps(steps);

        if (validationFailures.length() > 0) {
            JSONObject returnValue = new JSONObject();
            returnValue.put("resultCode", "validationFailed");
            returnValue.put("validationFailures", validationFailures);
            return returnValue.toString();
        }
        TestCase tc = testCaseService.saveTestCase(testCase);
        if (isNew) {
            userService.audit(userService.getUserByUserName(request.getUserPrincipal().getName()), "TC", tc.getId(), "CR" );
        }
        TestCaseInfo testCaseInfo2 = testCaseService.saveTestCaseInfo(testCaseInfo);

        JSONObject returnValue = new JSONObject();
        returnValue.put("resultCode", "success");
        returnValue.put("testCaseId", tc.getId());

        return returnValue.toString();
    }


    public void appendConstraintViolations(JSONObject validationFailures, Set constraintViolations, String prefix, String suffix) {
        for(Object o: constraintViolations) {
            if (o instanceof ConstraintViolation) {
                ConstraintViolation cv = (ConstraintViolation)o;
                validationFailures.put(prefix + cv.getRootBeanClass().getSimpleName().toLowerCase() + cv.getPropertyPath().toString() + suffix, cv.getMessage());
            }
        }
    }



}
