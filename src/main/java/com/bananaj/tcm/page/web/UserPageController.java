package com.bananaj.tcm.page.web;

import com.bananaj.banant.web.Renderer;
import com.bananaj.banant.web.TcmController;
import com.bananaj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userpage")
public class UserPageController extends TcmController {

    @Autowired
    private UserService userService;

    @RequestMapping("/dashboard")
    @ResponseBody
    public String dashboard() {
        return renderer.renderTemplate("userpage/dashboard");
    }
}
