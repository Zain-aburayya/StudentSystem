package com.example.systemservlet.servlet.analytics;

import com.example.systemservlet.database.Mysql;
import com.example.systemservlet.model.Course;
import com.example.systemservlet.service.AuthenticationService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CourseMin", value = "/CourseMin")
public class CourseMin extends HttpServlet {

    private final AuthenticationService authenticationService = AuthenticationService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!authenticationService.isAuthenticated()){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
            requestDispatcher.forward(request,response);
        }
        request.setAttribute("action" , "CourseMin");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("courses-form.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!authenticationService.isAuthenticated()){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
            requestDispatcher.forward(request,response);
        }
        int studentId = authenticationService.getStudentId();
        String courseName = request.getParameter("course");
        Double markk = Mysql.getInstance().getMIN(courseName);
        Course mark = new Course(courseName,markk);
        request.setAttribute("mark", mark);
        request.setAttribute("text", "MIN");
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/course-mark.jsp");
        requestsDispatcher.forward(request, response);
    }
}
