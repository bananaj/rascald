package com.bananaj.tcm.web.controller;

import com.bananaj.banant.web.TcmController;
import com.bananaj.tcm.domain.Step;
import com.bananaj.tcm.domain.StepComparator;
import com.bananaj.tcm.domain.TestCase;
import com.bananaj.tcm.service.ExecutionService;
import com.bananaj.tcm.service.TestCaseService;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/testcase")
public class TestCaseController extends TcmController {

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

}
