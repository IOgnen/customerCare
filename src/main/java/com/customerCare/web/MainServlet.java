package com.customerCare.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.ui.Model;

import java.io.IOException;

@CrossOrigin(origins = "*")
@WebServlet
public class MainServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public MainServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.springTemplateEngine.process("index.html", (IContext) getServletContext());

    }

}
