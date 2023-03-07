<%@ page import="com.example.systemservlet.model.Course" %>
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Average Result</title>
  <style>
    /* Set a background gradient */
    body {
      background-color: #f2f2f2;
      font-family: Arial, sans-serif;
    }

    h1 {
      text-align: center;
      color: #333;
      margin-top: 40px;
    }

    h2 {
      color: cornflowerblue;
      font-weight: bold;
    }

    #container {
      margin: 30px auto;
      text-align: center;
      max-width: 800px;
      padding: 20px;
      background-color: white;
      border-radius: 10px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    }

    p {
      display: block;
      margin: 40px auto;
      padding: 30px 20px;
      width: 300px;
      height: 30px;
      background-color: cornflowerblue;
      color: white;
      text-decoration: none;
      font-weight:bold;
      font-size:18px;
      border-radius: 5px;
      transition: background-color 0.2s ease-in-out;
    }
  </style>
</head>
<body>
<h1>Result</h1>
<%! Course course = new Course(); String text = "";%>
<%= course = (Course)request.getAttribute("mark")%>
<%= text = (String)request.getAttribute("text")%>
<p style="text-align:center;">The <strong><%=text%></strong> mark for <strong><%= course.getName() %></strong>
  is <strong><%= course.getMark() %></strong></p>
<form action="home.html">
  <button type="submit">Back</button>
</form>
</body>
</html>
