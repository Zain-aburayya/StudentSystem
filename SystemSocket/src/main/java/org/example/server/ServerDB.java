package org.example.server;

import org.example.database.Mysql;
import org.example.model.Course;

import java.util.ArrayList;
import java.util.List;

public class ServerDB {
    Mysql mysql;

    public ServerDB(){
        mysql = new Mysql();
    }

    public List<Course> getAllCourses(int id) {
        return mysql.getAllCourses(id);
    }

    public boolean isRegisteredStudent(int id) {
        return mysql.isRegisteredStudent(id);
    }

    public boolean isValidPassword(int id, String password) {
        return mysql.isValidPassword(id, password);
    }

    public Course getCourse(String courseName, int id) {
        return mysql.getCourse(courseName, id);
    }

    public double getAvg(String courseName) {
        return mysql.getAVG(courseName);
    }

    public double getMedian(String courseName){
        return mysql.getMEDIAN(courseName);
    }

    public double getMax(String courseName) {
        return mysql.getMAX(courseName);
    }

    public double getMin(String courseName) {
        return mysql.getMIN(courseName);
    }
}
