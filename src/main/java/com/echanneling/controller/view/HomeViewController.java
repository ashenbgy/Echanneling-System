package com.echanneling.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeViewController {

    @GetMapping(value = "/")
    public String homePage() {
        return "frontweb/indexv2.html";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "frontweb/indexv2.html";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "frontweb/indexv2.html";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "frontweb/about.html";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "frontweb/register.html";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "frontweb/indexv2.html";
    }
    
    @GetMapping(value = "/appointment")
    public String createAppointment() {
        return "frontweb/appointment.html";
    }
    
    @GetMapping(value = "/viewdoctor")
    public String viewDoctors() {
        return "frontweb/alldoctors.html";
    }

    @GetMapping(value = "/viewcenters")
    public String viewCenters() {
        return "frontweb/allCenters.html";
    }

    @GetMapping(value = "/contact")
    public String contactUs() {
        return "frontweb/contactvtwo.html";
    }

    @GetMapping(value = "/searchDetails")
    public String searchDetail() {
        return "frontweb/searchDetails.html";
    }
}
