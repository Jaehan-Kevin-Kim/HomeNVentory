<%-- 
    Document   : reset
    Created on : Apr 21, 2021, 9:39:56 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/CSS/styles.css" type="text/css" rel="stylesheet" />
      <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>        
        <p>Please enter your email address to reset your password</p>
        <form action="reset" method="POST">
            Email Address: <input type="text" name="resetPasswordEmail"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
