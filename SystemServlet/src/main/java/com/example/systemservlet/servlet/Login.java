package com.example.systemservlet.servlet;

import com.example.systemservlet.service.AuthenticationService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

    private AuthenticationService authenticationService = AuthenticationService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String direction = "/login.html";
        if(authenticationService.isAuthenticated()){
            direction = "/home.html";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(direction);
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(authenticationService.isAuthenticated()){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.html");
            requestDispatcher.forward(request,response);
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");

        authenticationService.authenticate(id, password);
        RequestDispatcher requestDispatcher;
        if (authenticationService.isAuthenticated()) {
            requestDispatcher = request.getRequestDispatcher("/home.html");
        } else {
            requestDispatcher = request.getRequestDispatcher("/fail-login.html");
        }
        requestDispatcher.forward(request,response);
    }
}
