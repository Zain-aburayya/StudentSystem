package com.example.systemservlet.database;

import com.example.systemservlet.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class Mysql {
    private final String HOST = "jdbc:mysql://localhost:3306/school?useSSL=false";
    private final String MYSQL_ROOT_USERNAME = "zain";
    private final String MYSQL_ROOT_PASSWORD = "penguin";
    private final String MYSQL_DATABASE = "school";
    private static Mysql instance = null;
    private Connection connection;

    private Mysql(){
        setConnection();
    }

    public static Mysql getInstance(){
        if(instance == null){
            instance = new Mysql();
        }
        return instance;
    }
    private void setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST , MYSQL_ROOT_USERNAME , MYSQL_ROOT_PASSWORD);
            System.out.println("Successfully Connected for Database.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCourseName(String courseName){
        return courseName.equalsIgnoreCase("math") ||
                courseName.equalsIgnoreCase("physics") ||
                courseName.equalsIgnoreCase("arabic");
    }
    private boolean isAnalyticsType(String analyticsType){
        return analyticsType.equalsIgnoreCase("avg") ||
                analyticsType.equalsIgnoreCase("max") ||
                analyticsType.equalsIgnoreCase("min");
    }
    private Course getAnalytics(String courseName , String analyticsType){
        if(!isCourseName(courseName) || !isAnalyticsType(analyticsType)){
            throw new NoSuchElementException("There is an error with course-name or analytics-type.");
        }
        Course course = null;
        try {
            Statement statement =connection.createStatement();
            ResultSet result =statement.executeQuery(
                    "SELECT " + analyticsType + "( " +courseName + ") FROM mark;"
            );
            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                course = new Course(courseName , result.getDouble(1));
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Course getMedian(String courseName){
        Course course = null;
        try {
            Statement statement =connection.createStatement();
            statement.execute("SET @row_index := -1");
            ResultSet result = statement.executeQuery(
                    "SELECT AVG( subq." + courseName + ") as median_value " +
                            "FROM (" +
                            "    SELECT @row_index:=@row_index + 1 AS row_index, " + courseName +
                            "    FROM mark" +
                            "    ORDER BY " + courseName +
                            "  ) AS subq" +
                            "  WHERE subq.row_index " +
                            "  IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))"
            );
            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                course = new Course(courseName , result.getDouble(1));
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Double getMIN(String courseName){
        return getAnalytics(courseName , "MIN").getMark();
    }

    public Double getMAX(String courseName){
        return getAnalytics(courseName , "MAX").getMark();
    }
    public Double getAVG(String courseName){
        return getAnalytics(courseName , "AVG").getMark();
    }

    public Double getMEDIAN(String courseName){
        return getMedian(courseName).getMark();
    }

    public ArrayList<Course> getAllCourses(int id){
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            String sql = "SELECT math, physics, arabic FROM mark WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                courseList.add(new Course("Math" , result.getDouble(1)));
                courseList.add(new Course("Physics" , result.getDouble(2)));
                courseList.add(new Course("Arabic" , result.getDouble(3)));
            }

            return courseList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Course getCourse(String courseName , int id){
        if(!isCourseName(courseName)){
            throw new NoSuchElementException("There is no course named " + courseName);
        }
        Course course = null;
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT " + courseName +
                            " FROM mark " +
                            "WHERE id = " + id
            );

            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                course = new Course(courseName, result.getDouble(1));
            }
            return course;
        }catch (Exception ex){
            throw new IllegalStateException();
        }
    }

    public boolean isRegisteredStudent(int id) {
        try {
            String sql = "SELECT name FROM student WHERE id =" + id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public boolean isValidPassword(int id, String password) {
        if (password == null) {
            throw new NullPointerException();
        }

        try {
            String sql = "SELECT password FROM student WHERE id =" + id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String pass = "";

            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                pass = result.getString(1);
            }

            return pass.equals(password);
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
