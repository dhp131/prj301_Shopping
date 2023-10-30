

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" type="text/css" href="your-stylesheet.css">
    </head>
    <body>
        <div class="admin-header">
            <h1>Welcome: ${sessionScope.LOGIN_USER.fullName}</h1>
            <div class="logout-container">
                <a href="MainController?action=Logout" class="logout-link">Logout</a>
            </div>
        </div>

        <div class="search-form-container">
            <form action="MainController" class="search-form">
                <input type="text" name="searchUser" value="${param.searchUser}" placeholder="Search User" />
                <input type="submit" value="SearchUser" name="action" class="search-button" />
            </form>

            <form action="MainController" class="search-form">
                <input type="text" name="ADsearchProduct" value="${param.ADsearchProduct}" placeholder="Search Product" />
                <input type="submit" value="ADSearchProduct" name="action" class="search-button" />
            </form>
        </div>

        <c:if test="${requestScope.LIST_USER !=null}">
            <c:if test="${not empty requestScope.LIST_USER}">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Password</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Role</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${user.userID}</td>
                                <td>
                                    <input type="text" name="fullName" value="${user.fullName}" required=""/>
                                </td>
                                <td>${user.password}</td>
                                <td>
                                    <input type="text" name="address" value="${user.address}" required=""/>
                                </td>
                                <td>
                                    <input type="text" name="email" value="${user.email}" required=""/>
                                </td>
                                <td>
                                    <input type="text" name="phone" value="${user.phone}" required=""/>
                                </td>
                                <td>
                                    <select name="roleID">
                                        <option value="AD" <c:if test="${user.roleID == 'AD'}">selected=""</c:if>>AD</option>
                                        <option value="US" <c:if test="${user.roleID == 'US'}">selected=""</c:if>>US</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="submit" name="action" value="Update" class="admin-action-button"/>
                                        <input type="hidden" name="search" value="${param.search}"/>
                                    <input type="hidden" name="userID" value="${user.userID}"/>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Delete" class="admin-action-button"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                    <input type="hidden" name="userID" value="${user.userID}"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>

    <c:if test="${requestScope.PRODUCTS != null}">
        <c:if test="${not empty requestScope.PRODUCTS}">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Create Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${requestScope.PRODUCTS}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.price}</td>
                            <td>${product.description}</td>
                            <td>${product.image}</td>
                            <td>${product.quantity}</td>
                            <td>${product.createDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>

    <p class="error-message">${requestScope.ERROR}</p>
</body>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
    }
    .admin-header {
        text-align: center;
        margin-bottom: 20px;
    }

    .logout-container{
        margin-top: 10px;

        padding: 8px 16px;
    }

    .logout-link {
        text-align: center;
        text-decoration: none;
        color: #fff;
        background-color: #333;
        padding: 8px 16px;
        border-radius: 8px;
        transition: transform 0.3s ease;
    }

    .logout-link:hover{
        transform: scale(1.05);
    }
    .search-form-container {
        display: flex;
        justify-content: space-evenly;
        margin-bottom: 20px;
    }

    .search-form {
        display: flex;
    }

    .search-form input[type="text"] {
        padding: 5px;
    }

    .search-button {
        padding: 5px 10px;
        background-color: #333;
        color: #fff;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        margin-left: 8px;
    }

    .admin-table {
        width: 100%;
        border-collapse: collapse;
    }

    .admin-table th,
    .admin-table td {
        padding: 8px;
        text-align: left;
    }

    .admin-table th {
        background-color: #f2f2f2;
    }

    .admin-table tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .admin-table tbody tr:hover {
        background-color: #eaf5ff;
    }

    .admin-action-button {
        padding: 5px 10px;
        background-color: #333;
        color: #fff;
        border: none;
        border-radius: 8px;
        cursor: pointer;
    }

    .error-message {
        color: red;
        font-weight: bold;
    }


</style>
</html>

