<%-- 
    Document   : register
    Created on : Apr 13, 2021, 12:29:31 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="register" method="post">
        <h1>Sign Up</h1>
        <h3>Please enter below information to sign up.</h3>
        <span>Email: </span><input type="email" name="email" required="required"/> <br>
        <span>Password: </span><input type="password" name="password" required="required"/><br>
        <span>First Name: </span><input type="text" name="firstName" required="required"/><br>
        <span>Last Name: </span><input type="text" name="lastName" required="required"/><br>
        <input type="submit" name="submit" value="Register" /><br>
        </form>
    </body>
</html>
