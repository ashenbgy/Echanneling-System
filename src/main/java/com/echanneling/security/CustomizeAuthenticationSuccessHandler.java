package com.echanneling.security;

import com.echanneling.dto.MediUserDTO;
import com.echanneling.entity.User;
import com.echanneling.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

       boolean admin = false;

        logger.info("AT onAuthenticationSuccess(...) function!");
        MediUserDTO authUser = (MediUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userService.findById(authUser.getUserId());

        //**********Session Creation ********
        HttpSession session = request.getSession();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());


        //***********************************
        GrantedAuthority role = authUser.getAuthorities().stream().findFirst().get();
        //String page="about";

        if(role!=null && role.getAuthority().equals("ROLE_Admin")){
            response.sendRedirect("dashboard");
        }else if(role!=null && role.getAuthority().equals("ROLE_Patient")){
            response.sendRedirect("home");
        }else if(role!=null && role.getAuthority().equals("ROLE_Doctor")){
            response.sendRedirect("dashboard");
        }else if(role!=null && role.getAuthority().equals("ROLE_Center")){
            response.sendRedirect("dashboard");
        }

    }

}
