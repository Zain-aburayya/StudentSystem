<!DOCTYPE html>
<html lang = "en">
<head>
    <title> index </title>
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

        a {
            display: block;
            margin: 10px auto;
            padding: 10px 20px;
            width: 200px;
            background-color: cornflowerblue;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.2s ease-in-out;
        }

        a:hover {
            background-color: green;
        }
        #uniqueid:hover {
            background-color: red;
        }
    </style>
</head>
<body>
<h1> Student System </h1>
<h3 style = "text-align: center"> Welcome ... </h3>
<div id = "container">
    <form action="login">
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>