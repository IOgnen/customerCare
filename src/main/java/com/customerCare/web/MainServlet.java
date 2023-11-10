package com.customerCare.web;

import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainServlet extends HttpServlet {

    @GetMapping
    public String doGet() {
        return "index";
    }

}
