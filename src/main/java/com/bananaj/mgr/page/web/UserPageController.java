package com.bananaj.mgr.page.web;

import com.bananaj.banant.web.NitroController;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userpage")
public class UserPageController extends NitroController {

    @Autowired
    private UserService userService;

    @RequestMapping("/dashboard")
    @ResponseBody
    public String dashboard() {
        return renderer.renderTemplate("userpage/dashboard");
    }
}
