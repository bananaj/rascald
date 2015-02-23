package com.bananaj.common.web;

import com.bananaj.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cio on 21/02/15.
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private UserService userService;

    public AuthenticationSuccessHandler() {
        super();
    }

    public AuthenticationSuccessHandler(String defaultTargetUrl) {
        super(defaultTargetUrl);
    }



    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        userService.audit(userService.getUserByUserName(authentication.getName()), "SY", "SY", "LGN");
        this.handle(request, response, authentication);
        this.clearAuthenticationAttributes(request);

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
