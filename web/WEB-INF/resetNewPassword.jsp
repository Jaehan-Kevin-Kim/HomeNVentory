<%-- 
    Document   : resetNewPassword
    Created on : Apr 21, 2021, 9:38:45 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Reset Password</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form name="reset" method="POST">
            <input type="password" name="newPassword">
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
