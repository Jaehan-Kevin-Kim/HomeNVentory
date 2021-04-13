<%-- 
    Document   : admin
    Created on : Mar 28, 2021, 9:26:54 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
        <h1>Admin</h1>
        <section>
            <h4>Menu</h4>
            <ul>
                <li> <a href="inventory">Inventory</a></li>
                <li> <a href="admin">Admin</a></li>
                <li> <a href="login?logout">Logout</a></li>
            </ul>
        </section>
        <section>
            <h2>Manage Users</h2>
            <table>
                <tr>
                    <th><h4>Email</h4></th>
                    <th><h4>First Name</h4></th>
                    <th><h4>Last Name</h4></th>
                    <th><h4>Delete</h4></th>
                    <th><h4>Edit</h4></th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                    <form action="admin" method="post">
                        <td>
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="email" value="${user.email}">
                            <c:if test="${user.getRole().getRoleId() ne 2}">
                                <input type="submit" value="Delete" disabled="true">
                            </c:if>
                            <c:if test="${user.getRole().getRoleId() eq 2}">
                                <input type="submit" value="Delete">
                            </c:if>
                        </td>
                    </form>
                    <form action="admin" method="post">
                        <td>
                            <input type="hidden" name="action" value="editActivation">
                            <input type="hidden" name="email" value="${user.email}">
                            <input type="submit" value="Edit">
                        </td>
                    </form>

                    </tr>     
                </c:forEach> 
            </table>
        </section>

        <c:if test="${editActivation ne null}">
            <section>
                <h3>Edit User</h3>
                <form action="admin" method="post">
                    <table>
                        <tr>
                            <th><h4>Email</h4></th>
                            <th><h4>First Name</h4></th>
                            <th><h4>Last Name</h4></th>
                            <th><h4>Password</h4></th>
                        </tr>
                        <tr>    
                            <td><input type="text" value="${user.email}" name="editEmail" readonly></td>
                            <td><input type="text" value="${user.firstName}" name="editFirstName"></td>
                            <td><input type="text" value="${user.lastName}" name="editLastName"></td>
                            <td><input type="password" value="${user.password}" name="editPassword"></td>
                        </tr>

                    </table>
                    <input type="hidden" name="action" value="editUser" />
                    <input type="submit" name="edit" value="Save" />
                </form>
                <form action="admin" method="post">
                    <input type="hidden" name="action" value="cancel" /> 
                    <input type="submit" name="Cancel" value="Cancel" /> <br />
                </form>
            </section>
        </c:if>

        <section>
            <h3>Add User</h3>
            <form action="admin" method="post">
                <span>Email: </span>
                <input type="text" name="email"/> <br />
                <span>First Name: </span>
                <input type="text" name="firstName"/> <br />
                <span>Last Name: </span>
                <input type="text" name="lastName"/> <br />
                <span>Password: </span>
                <input type="password" name="password" /> <br />
                <input type="submit" name="save" value="Save" /> <br />
                <input type="hidden" name="action" value="addUser" /> <br />
            </form>
        </section>
        <div>
            ${msg}
        </div>

    </body>
</html>
