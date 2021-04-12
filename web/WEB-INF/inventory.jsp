<%-- 
    Document   : inventory
    Created on : Mar 28, 2021, 9:27:04 AM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
        <style>
            table, th, td {
                border: 1px solid black;
            }
            td, th {
                width: 150px;
            }
        </style>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <section>
            <h4>Menu</h4>
            <ul>
                <li> <a href="inventory">Inventory</a></li>
                <li> <a href="admin">Admin</a></li>
                <li> <a href="login?logout">Logout</a></li>
            </ul>
        </section>
        <section>
            <h2>Inventory for ${user.firstName} ${user.lastName}</h2>
            <table>
                <tr>
                    <th><h4>Category</h4></th>
                    <th><h4>Name</h4></th>
                    <th><h4>Price</h4></th>
                    <th><h4>   </h4></th>
                </tr>
                <c:forEach items="${items}" var="item">
                    <form action="inventory" method="post">
                        <tr>
                            <td>${item.category.categoryName}</td>
                            <td>${item.itemName}</td>
                            <td>$${item.price}</td>
                            <td>
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="itemId" value="${item.itemId}">
                                <input type="submit" value="Delete">
                                <!--<button>Delete</button>-->
                            </td>
                        </tr>     
                    </form>
                </c:forEach> 
            </table>
        </section>
        <section>
            <h3>Add Item</h3>
            <form action="inventory" method="post">
                <span>Category: </span>
                <select name="categories" value="">
                    <c:forEach items="${categories}" var="category">
                        <option name="category" value ="${category.categoryName}">${category.categoryName}</option>
                    </c:forEach>
                </select> <br />
                <span>Name: </span>
                <input type="text" name="name"/> <br />
                <span>Price: </span>
                <input type="text" name="price"/> <br />
                <input type="submit" name="save" value="Save" /> <br />
                <input type="hidden" name="action" value="addItem" /> <br />
            </form>
        </section>
        <div>
            ${msg}
        </div>
    </body>
</html>
