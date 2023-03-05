package com.example.systemspring.service;

import com.example.systemspring.model.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DatabaseService {
    private JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isValidStudent(int studentId) {
        String SQL = "SELECT password FROM student WHERE id = ?";
        return jdbcTemplate.query(SQL,new Object[]{studentId} , new PasswordRowMapper()).size();
    }

    public boolean isValidPassword(int studentId, String password) {
        final String SQL = "SELECT password FROM student WHERE id = ?";
        String correctPassword = jdbcTemplate.query(SQL , new Object[]{studentId}
                                                , new PasswordRowMapper()).get(0);
        return correctPassword.equals(password);
    }

    public ArrayList<Course> getAllStudentMarks(int studentId)  {
        final String SQL = "SELECT math, physics, arabic FROM mark WHERE id = ?";
        ArrayList<Course> courses = jdbcTemplate.query(SQL , new Object[]{studentId}, new MarksRowMapper()).get(0);
        return courses;
    }

    public Course getStudentMark(int studentId, String courseName)  {
        final String SQL = "SELECT " + courseName + " FROM mark WHERE id = ?";
        Double mark = jdbcTemplate.query(SQL, new Object[]{studentId}, new MarkRowMapper()).get(0);
        return new Course(courseName, mark);
    }

    public Course getAvg(String courseName) {
        final String SQL = "SELECT AVG( ? ) FROM mark";
        Double mark = jdbcTemplate.query(SQL , new Object[]{courseName} , new MarkRowMapper()).get(0);
        return new Course(courseName, mark);
    }

    public Course getMax(String courseName) {
        final String SQL = "SELECT MAX( ? ) FROM mark";
        Double mark = jdbcTemplate.query(SQL, new Object[]{courseName} , new MarkRowMapper()).get(0);
        return new Course(courseName, mark);
    }

    public Course getMin(String courseName) {
        final String SQL = "SELECT MIN( ? ) FROM mark";
        Double mark = jdbcTemplate.query(SQL, new Object[]{courseName} , new MarkRowMapper()).get(0);
        return new Course(courseName, mark);
    }

    public Course getMedian(String courseName){
        final String SQL = "SELECT AVG( subq." + courseName + ") as median_value " +
                "FROM (" +
                "    SELECT @row_index:=@row_index + 1 AS row_index, " + courseName +
                "    FROM mark" +
                "    ORDER BY " + courseName +
                "  ) AS subq" +
                "  WHERE subq.row_index " +
                "  IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))";
        Double mark = jdbcTemplate.query(SQL, new Object[]{courseName}).get(0);
        return new Course(courseName, mark);
    }

}
