<%--
  Created by IntelliJ IDEA.
  User: zain
  Date: 06/03/2023
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Choose</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 40px;
        }

        form {
            margin: 30px auto;
            text-align: center;
            max-width: 800px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-size: 18px;
            color: #333;
            font-weight: bold;
        }

        select {
            display: block;
            margin-bottom: 20px;
            padding: 20px;
            font-size: 16px;
            color: #333;
            background-color: #f2f2f2;
            border: none;
            border-radius: 5px;
        }

        input[type="submit"],
        input[type="reset"] {
            display: inline-block;
            margin-right: 10px;
            padding: 10px 20px;
            background-color: cornflowerblue;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: green;
        }
        form {
            max-width: 800px;
            margin: 0 auto;
            text-align: center;
        }
    </style>
</head>
<body>
<form action="<%=(String)request.getAttribute("action")%>" method="post">
    <label for="course"> Choose Course </label>
    <select name="course" id="course">
        <option value="math">Math</option>
        <option value="physics">Physics</option>
        <option value="arabic">Arabic</option>
    </select>
    <p>
        <input type="submit" value="Submit" />
    </p>
</form>
</body>
</html>

