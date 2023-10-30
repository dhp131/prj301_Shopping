

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" type="text/css" href="/css/usPage.css">-->
        <title>USER Page</title>
    </head>
    <body>
        <div class="user-info">
            <h1>User's Information</h1>
            <div class="btn-link">
                <a class="back-link" href="MainController?action=Search%20Product">Back</a>
                <a class="logout-link" href="MainController?action=Logout">Logout</a></br>
            </div>

            <div class="information">
                <div class="table-container">
                    <table border="1">
                        <tbody>
                        <form action="MainController" method="POST">
                            <tr>
                                <td>User ID</td>
                                <td>
                                    <input type="text" name="userID" value="${sessionScope.LOGIN_USER.userID}" readonly=""/>
                                </td>
                            </tr>
                            <tr>
                                <td>Full Name</td>
                                <td>
                                    <input type="text" name="fullName" value="${sessionScope.LOGIN_USER.fullName}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>
                                    <input type="text" name="userID" value="${sessionScope.LOGIN_USER.email}" readonly=""/>
                                </td>
                            </tr>
                            <tr>
                                <td>Phone</td>
                                <td>
                                    <input type="text" name="phone" value="${sessionScope.LOGIN_USER.phone}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td>
                                    <input type="text" name="address" value="${sessionScope.LOGIN_USER.address}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td>
                                    <input type="password" name="password" value="${sessionScope.LOGIN_USER.password}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Confirm Password</td>
                                <td>
                                    <input type="password" name="confirm" value="${sessionScope.LOGIN_USER.password}" />
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <div class="msg-N-btn">
                                        <div class="msg-container">
                                            <p class="success-msg">${requestScope.MESSAGE}</p>
                                            <p class="error-msg">${requestScope.EROR_MESSAGE}</p>
                                        </div>
                                        <div class="action-btn">
                                            <input type="submit" value="Save" name="action" />
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </div>

                <div class="order-list">
                    <c:forEach var="order" items="${requestScope.ORDER}">
                        <div class="order-container">
                            <div class="order-id">
                                <p>Order ID: ${order.id}</p>
                            </div>

                            <div class="order-date">
                                <p>Date: ${order.orderDate}</p>
                            </div>

                            <div class="order-items">
                                <h3>Order Items:</h3>
                                <ul>
                                    <c:forEach var="orderDetail" items="${order.orderDetails}">
                                        <li>
                                            ${orderDetail.product.name} - X${orderDetail.quantity} - Price: ${orderDetail.price * orderDetail.quantity}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <div class="order-total">
                                <p>Total Price: ${order.totalPrice}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
    
        <style>
        body {
            margin: 20px;
            font-family: Arial, sans-serif;
        }

        .user-info {
            margin-bottom: 20px;
        }

        .user-info h1 {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .order-list{
            width: 40%;
        }

        .order-container {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
            margin-left: 12px;
        }

        .order-id,
        .order-date,
        .order-items,
        .order-total {
            margin-bottom: 10px;
        }

        .order-items h3 {
            font-size: 18px;
            margin-bottom: 10px;
        }

        .order-items ul {
            list-style-type: none;
            padding: 0;
            padding: 0px 0px 10px;
            border-bottom: 1px solid #ccc;
        }

        .order-items li {
            margin-bottom: 5px;
        }

        .order-total p {
            font-weight: bold;
            margin: 0px;
        }

        .order-id p,
        .order-date p{
            margin: 0px;
            padding: 0px 0px 10px;
            border-bottom: 1px solid #ccc;
        }
        .btn-link{
            display: flex;
        }
        .back-link,
        .logout-link{
            text-decoration: none;
            background-color: #333;
            color: #fff;
            padding: 8px 16px;
            margin: 8px;
            border-radius: 8px;
        }

        .back-link:hover,
        .logout-link:hover{
            background-color: #555;
            transform: scale(1.05);
        }

        table {
            border-collapse: collapse;
            width: 100%;
            height: 400px;
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        input[type="text"],
        input[type="password"]{
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .action-btn {
            text-align: right;
        }
        .action-btn input{
            border: none;
            padding: 8px 16px;
            border-radius: 8px;
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }

        .action-btn input:hover{
            background-color: #555;
            transform: scale(1.05);
        }

        .table-container{
            display: flex;
            width: 50%;
            margin-right: 12px;
        }

        .password-table{
            height: 100px;
            width: 400px;
            margin-left: 20px;
        }

        .information{
            display: flex;
        }
        
        .msg-N-btn{
            display: flex;
            justify-content: space-between;
        }
        
        .msg-container{
            display: flex;
            align-items: center;
        }
        
        .msg-container p{
            margin: 0px;
        }
        
        .success-msg{
            color: #27cb2c;
        }
        .error-msg{
            color: #dc3545;
        }
    </style>
 
</html> 
