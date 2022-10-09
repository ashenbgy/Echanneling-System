package com.echanneling.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashBoardController {
    @GetMapping(value = "/dashboard")
    public String homePage() {
        return "backweb/dashboard.html";
    }

    @GetMapping(value = "/appointments")
    public String manageAppointments() {
        return "backweb/appointments.html";
    }

    @GetMapping(value = "/doctors")
    public String manageDoctors() {
        return "backweb/doctors.html";
    }

    @GetMapping(value = "/patients")
    public String managePatients() {
        return "backweb/patients.html";
    }

    @GetMapping(value = "/centers")
    public String manageChannelCenters() {
        return "backweb/centers.html";
    }
    
    @GetMapping(value = "/registerCenter")
    public String registerCenter() {
        return "backweb/regcenter.html";
    }

    @GetMapping(value = "/articles")
    public String manageArticles() {
        return "backweb/articles.html";
    }

    @GetMapping(value = "/services")
    public String manageServices() {
        return "backweb/services.html";
    }

    @GetMapping(value = "/settings")
    public String manageSettings() {
        return "backweb/settings.html";
    }
    
    @GetMapping(value = "/docreport")
    public String docReport() { 
        return "backweb/docreport.html";
    }

}
