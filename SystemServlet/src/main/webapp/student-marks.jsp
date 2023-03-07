<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.systemservlet.model.Course" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Marks</title>
  <style>
    body {
      background-color: #f2f2f2;
      font-family: Arial, sans-serif;
    }
    css
    Copy code
    h1 {
      text-align: center;
      color: #333;
      margin-top: 40px;
    }

    table {
      margin: 30px auto;
      max-width: 800px;
      padding: 20px;
      background-color: white;
      border-radius: 10px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
      border-collapse: collapse;
    }

    th, td {
      text-align: left;
      padding: 12px;
    }

    th {
      background-color: #007bff;
      color: white;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #ddd;
    }
  </style>
</head>
<body>
<h1>Student Marks</h1>
<table>
  <thead>
  <tr>
    <th>Course Name</th>
    <th>Marks</th>
  </tr>
  </thead>
  <tbody>
  <%! ArrayList<Course> courses = new ArrayList<>(); %>
  <% courses = (ArrayList<Course>)request.getAttribute("courses"); %>
  <% for (Course mark : courses) { %>
    <tr>
      <td><%= mark.getName()%></td>
      <td><%= mark.getMark()%></td>
    </tr>
  <% } %>
  </tbody>
</table>
</body>
</html>