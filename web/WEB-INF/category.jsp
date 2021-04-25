<%-- 
    Document   : category
    Created on : Apr 13, 2021, 2:26:53 PM
    Author     : 841898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/CSS/styles.css" type="text/css" rel="stylesheet" />
        <title>Category Page</title>
        <style>
            table, th, td {
                border: 1px solid black;
            }
            td {
                padding: 0 10px;
            }
        </style> 
    </head>
    <body>
        <h1>Category</h1>
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
        <section>
            <section>
                <h2>Edit Categories</h2>
                <table>
                    <tr>
                        <th><h4>Category</h4></th>
                        <th><h4>   </h4></th>
                    </tr>
                    <c:forEach items="${categories}" var="category">
                        <form action="category" method="post">
                            <tr>
                                <td>${category.categoryName}</td>
                                <td>
                                    <input type="hidden" name="action" value="editActivation">
                                    <input type="hidden" name="categoryId" value="${category.categoryId}">
                                    <input type="submit" value="Edit">
                                </td>
                            </tr>     
                        </form>
                    </c:forEach> 
                </table>
            </section>
            
            <c:if test="${editActivation ne null}">
            <section>
                <h3>Edit Category</h3>
                <form action="category" method="post">
                    <table>
                        <tr>
                            <th><h4>Category Name</h4></th>
                        </tr>
                        <tr>    
                            <td><input type="text" value="${category.categoryName}" name="editCategoryName"></td>
                        </tr>
                    </table>
                    <input type="hidden" name="action" value="editCategory" />
                    <input type="hidden" name="categoryId" value="${category.categoryId}">
                    <input type="submit" name="editCategory" value="Save" />
                </form>
                <form action="admin" method="post">
                    <input type="hidden" name="action" value="cancel" /> 
                    <input type="submit" name="Cancel" value="Cancel" /> <br />
                </form>
            </section>
        </c:if>
            
          <section>
            <h3>Add Category</h3>
            <form action="category" method="post">
                <span>Category: </span>
                <input type="text" name="categoryName"/> <br />
                <input type="submit" name="save" value="Save" /> <br />
                <input type="hidden" name="action" value="addCategory" /> <br />
            </form>
        </section>
        <div>
            ${msg}
        </div>


    </body>
</html>
