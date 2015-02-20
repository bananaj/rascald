package com.bananaj.tcm.web.controller;

import com.bananaj.banant.web.TcmController;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends TcmController {

    @Autowired
    private UserService userService;

    @RequestMapping("/greeting")
    @ResponseBody
    public String greeting() {
        return "greeting jonesing";
    }

    @RequestMapping("/somebody")
    @ResponseBody
    public String helloworld() {
        return userService.saySomething();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("userList", "userlistpara");

        return "user/index";
    }

}
