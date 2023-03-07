package com.example.systemservlet.service;

import com.example.systemservlet.database.Mysql;

public class AuthenticationService {
    Mysql dataBase = Mysql.getInstance();

    private boolean isAuthenticated = false;
    private int studentId;
    private static AuthenticationService instance = null;

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public void reset(){
        System.out.println("zain == ");
        instance = null;
    }

    private AuthenticationService() { }

    private void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public boolean authenticate(int id, String password) {
        if (!dataBase.isRegisteredStudent(id)) {
            isAuthenticated = false;
        }
        if (!dataBase.isValidPassword(id, password)) {
            isAuthenticated = false;
        }
        else {
            isAuthenticated = true;
            setStudentId(id);
            System.out.println(id);
        }
        return isAuthenticated;
    }
}
