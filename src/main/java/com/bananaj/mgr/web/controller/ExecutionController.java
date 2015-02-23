package com.bananaj.mgr.web.controller;

import com.bananaj.common.web.MgrController;
import com.bananaj.mgr.domain.*;
import com.bananaj.mgr.service.ExecutionService;
import com.bananaj.mgr.service.TestCaseService;
import com.bananaj.user.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/execution")
public class ExecutionController extends MgrController {

    @Autowired
    private ExecutionService executionService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private UserService userService;


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


    @RequestMapping("/execution/{executionId}/steps")
    @ResponseBody
    public String executionsteps(@PathVariable String executionId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        Execution execution = executionService.findExecutionById(executionId);
        context.put("execution", execution);
        context.put("testCaseInfo", execution.getTestCaseInfo());
        context.put("steps", execution.getTestCaseInfo().getSteps());
        context.put("executionsteps", execution.getSteps());
        return renderer.renderTemplate("testcase/executesteps", context);
    }

    @RequestMapping("/execution/{executionId}/{stepId}/onestep")
    @ResponseBody
    public String executeonestep(@PathVariable String executionId, @PathVariable String stepId, HttpSession session, HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("stepId", stepId);
        context.put("executionId", executionId);
        return renderer.renderTemplate("testcase/executestep", context);
    }

    @RequestMapping("/execution/onestep/save")
    @ResponseBody
    public String executeonestepsave(@ModelAttribute("model") ModelMap model, HttpSession session, HttpServletRequest request) {
        Execution execution = executionService.findExecutionById(request.getParameter("executionId"));
        ExecutionStep executionStep = new ExecutionStep();
        executionStep.setExecution(execution);
        executionStep.setResultCode(request.getParameter("resultCode"));
        executionStep.setComments(request.getParameter("comments"));
        executionStep.setDate(new Date());
        executionStep.setUser(userService.getUserByUserName(request.getUserPrincipal().getName()));
        executionStep.setStepId(request.getParameter("stepId"));
        executionService.saveExecutionStep(executionStep);
        return new JSONObject().toString();
    }

}
