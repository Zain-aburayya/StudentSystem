package com.example.systemservlet.servlet;

import com.example.systemservlet.database.Mysql;
import com.example.systemservlet.model.Course;
import com.example.systemservlet.service.AuthenticationService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "StudentMarks", value = "/StudentMarks")
public class StudentMarks extends HttpServlet {

    private Mysql mysql = Mysql.getInstance();
    private AuthenticationService authenticationService = AuthenticationService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!authenticationService.isAuthenticated()){
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/login.html");
            requestsDispatcher.forward(request, response);
        }
        int studentId = authenticationService.getStudentId();
        ArrayList<Course> courses = Mysql.getInstance().getAllCourses(studentId);
        request.setAttribute("courses", courses);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/student-marks.jsp");
        requestsDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // NOTHING TO DO HERE : )
    }
}
