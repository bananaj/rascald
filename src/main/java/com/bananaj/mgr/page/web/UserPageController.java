package com.bananaj.mgr.page.web;

import com.bananaj.common.web.MgrController;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userpage")
public class UserPageController extends MgrController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return renderer.renderTemplate("userpage/home");
    }
}
