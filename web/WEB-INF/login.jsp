<%-- 
    Document   : login
    Created on : Mar 28, 2021, 9:27:11 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Inventory</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form action="login" method="POST">
            Email: <input type="text" name="email" value="${email}"><br>
            Password: <input type="password" name="password" value="${password}"><br>
            <input type="submit" value="Log In">
        </form>
     
        <div>
            ${msg} <br />
        </div>
        <br />
        <div>
        <a href="register">Create an Account</a>
                <a href="reset">Forgot password</a>
        </div>
    </body>
</html>
