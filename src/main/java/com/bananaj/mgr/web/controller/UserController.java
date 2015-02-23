package com.bananaj.mgr.web.controller;

import com.bananaj.common.web.MgrController;
import com.bananaj.user.domain.User;
import com.bananaj.user.domain.UserAudit;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends MgrController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("userList", "userlistpara");

        return "user/index";
    }

    @RequestMapping("/actionslatest")
    @ResponseBody
    public String actionslatest(HttpServletRequest request) {

        User user = userService.getUserByUserName(request.getUserPrincipal().getName());

        List<UserAudit> actionslatest = userService.findLatestActionsForUser(user);

        Map<String, Object> context = renderer.getBaseContext();

        context.put("actionslatest", actionslatest);
        return renderer.renderTemplate("user/actionslatest", context);

    }
}
