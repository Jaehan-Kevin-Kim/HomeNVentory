<%-- 
    Document   : account
    Created on : Apr 13, 2021, 12:50:33 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/CSS/styles.css" type="text/css" rel="stylesheet" />
        <title>Account</title>
        <style>
            table, th, td {
                border: 1px solid black;
            }
            td {
                width: 150px;
            }
        </style> 
    </head>
    <body>
        <h1>Account</h1>
        <section>
            <h4>Menu</h4>
             <ul>
                <li> <a href="inventory">Inventory</a></li>
                <li> <a href="admin">Admin</a></li>
                <li> <a href="account">Account</a></li>
                <li> <a href="category">Category</a></li>
                <li> <a href="login?logout">Logout</a></li>
            </ul>
        </section>

        <h2>Account Information for ${user.firstName} ${user.lastName}</h2>
        
        <section>
            <h3>Edit Account</h3>
            <form action="account" method="post">
                <table>
                    <tr>
                        <th><h4>Email</h4></th>
                        <th><h4>First Name</h4></th>
                        <th><h4>Last Name</h4></th>
                        <th><h4>Password</h4></th>
                        <th><h4>Active</h4></th>
                    </tr>
                    <tr>    
                        <td><input type="text" value="${user.email}" name="editEmail" readonly></td>
                        <td><input type="text" value="${user.firstName}" name="editFirstName"></td>
                        <td><input type="text" value="${user.lastName}" name="editLastName"></td>
                        <td><input type="password" value="${user.password}" name="editPassword"></td>
                        <c:choose>
                            <c:when test="${user.active == true}">       
                                <td><input type="checkbox" checked  value="checked" name="editActive"></td>
                            </c:when>
                            <c:otherwise>
                                <td><input type="checkbox" disabled="true"></td>       
                            </c:otherwise>
                        </c:choose>
                        

                    </tr>

                </table>
                <input type="hidden" name="action" value="editAccount" />
                <input type="submit" name="edit" value="Save" />
            </form>
            <form action="admin" method="post">
                <input type="hidden" name="action" value="cancel" /> 
                <input type="submit" name="Cancel" value="Cancel" /> <br />
            </form>
        </section>

        ${msg}
    </body>
</html>
