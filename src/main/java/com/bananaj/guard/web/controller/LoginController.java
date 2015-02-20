package com.bananaj.guard.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String login(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("userList", "userlistpara");

        return "guard/login";
    }

    @RequestMapping(value = "/LoginPage", method = RequestMethod.GET)
    public String loginPage(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("userList", "userlistpara");
        return "guard/login";
    }

}
