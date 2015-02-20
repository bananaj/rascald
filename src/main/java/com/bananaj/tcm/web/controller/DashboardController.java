package com.bananaj.tcm.web.controller;

import com.bananaj.banant.web.TcmController;
import com.bananaj.tcm.domain.Project;
import com.bananaj.tcm.service.ProjectService;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends TcmController {

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
