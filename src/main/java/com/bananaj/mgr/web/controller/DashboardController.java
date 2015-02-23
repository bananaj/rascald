package com.bananaj.mgr.web.controller;

import com.bananaj.common.web.MgrController;
import com.bananaj.mgr.service.ProjectService;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends MgrController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    @ResponseBody
    public String projects(HttpServletRequest request) {
        Map<String, Object> context = renderer.getBaseContext();
        context.put("projects", projectService.findProjectsForUsername(request.getUserPrincipal().getName()));
        return renderer.renderTemplate("dashboard/projects", context);
    }

}
