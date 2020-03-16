package com.justindodson.rusticridesgarage.admin.controllers;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "admin/login";
    }
}
