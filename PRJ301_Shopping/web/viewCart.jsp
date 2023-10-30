<%-- 
    Document   : viewCart
    Created on : Jun 15, 2023, 10:24:14 AM
    Author     : ngohu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.shopping.Tea"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
    </head>
    <body>
        <div class="check-out-menu">
            <div class="products-container">
                <c:if test="${sessionScope.CART != null}">
                    <c:if test="${not empty sessionScope.CART.cart}">
                        <c:set var="totalBill" value="0"/>
                        <c:forEach var="cart" items="${sessionScope.CART.cart}">
                            <div class="item-container" style="">
                                <form action="MainController" method="POST">
                                    <div class="item-img">
                                        <img src="${cart.value.product.image}" alt="${cart.value.product.name}" style="width: 200px; height: 200px;">
                                    </div>
                                    <div class="item-name">
                                        <h3>${cart.value.product.name}</h3>
                                    </div>
                                    <div class="item-price">
                                        <p>Total: </p>
                                        <h2>${cart.value.product.price * cart.value.quantity}$</h2>
                                        <c:set var="totalBill" value="${totalBill + cart.value.product.price * cart.value.quantity}"/>
                                    </div>
                                    <div class="quantity-choice">
                                        <label for="numberInput">Quantity:</label>
                                        <input class="quantity-label" name="quantity" type="number" id="quantityInput${cart.value.product.id}" value="${cart.value.quantity}" readonly=""/>

                                        <div class="quantity-btn">
                                            <button onclick="increment(${cart.value.product.id}, event)">+</button>
                                            <button onclick="decrement(${cart.value.product.id}, event)">-</button>
                                        </div>
                                    </div>
                                    <div class="submit-btn">
                                        <input type="hidden" name="productID" value="${cart.value.product.id}" />
                                        <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}" />
                                        <input class="edit-btn" type="submit" value="Edit" name="action"/>
                                        <input class="remove-btn" type="submit" value="Remove" name="action"/>
                                    </div> 
                                </form>
                            </div>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>

            <div class="check-out-infor">
                <div class="check-out-header">
                    <h1>Total</h1>
                </div>
                <div class="item-price">
                    <h2 id="total-bill">${totalBill}</h2>
                </div>

                <div class="click-link">
                    <div class="check-out-link">
                        <a class="btn" href="MainController?action=ViewCheckOut&userID=${sessionScope.LOGIN_USER.userID}">Check Out</a>
                    </div>
                    <div class="back-link">
                        <a class="btn" href="MainController?action=Search%20Product">Back</a>
                    </div>
                </div>
            </div>

        </div>
    </body>
    <style>
        body {
            margin: 20px;
            font-family: Arial, sans-serif;
        }

        .products-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            width: 70%;
            border-right: 1px solid #ccc;
        }

        .item-container {
            width: 250px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-align: center;
        }

        .item-img img {
            width: 200px;
            height: 200px;
        }

        .item-name h3 {
            margin-top: 10px;
            font-size: 18px;
        }

        .item-price h2 {
            margin-top: 10px;
            color: #007bff;
            text-align: center;
        }

        .item-description p {
            margin-top: 10px;
            font-size: 14px;
        }
        .quantity-choice{
            display: flex;
        }

        .check-out-menu{
            border: 1px solid #ccc;
            margin-left: 40px;
            padding: 16px;
            display: flex;
        }

        .check-out-infor{
            margin-left: 40px;
            width: 20%;
        }

        .check-out-header{
            text-align: center;
        }

        .click-link{
            text-align: center;
        }

        .click-link a{
            display: inline-block;
            padding: 10px;
            background-color: #333;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn{
            background-color: #333;
            margin-right: 10px;
            transition: background-color 0.3s ease;
            margin: 8px 0px;
        }

        .btn:hover{
            transform: scale(1.05);
        }

        .back-link a.btn{
            padding: 10px 30px;
        }

        .quantity-choice {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-top: 20px;
            align-items: center;
        }

        .quantity-choice label {
            font-size: 14px;
            margin-right: 10px;
        }

        .quantity-label {
            width: 50px;
            padding: 5px;
            text-align: center;
            border: none;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-right: 8px;
        }

        .quantity-btn {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .quantity-btn button {
            padding: 5px 10px;
            color: #333;
            border: none;
            border-radius: 5px;
            margin-right: 5px;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .quantity-btn button:hover{
            background-color: #333;
            color: #fff;
            transform: scale(1.05);
        }

        .submit-btn{
            margin-top: 8px;
        }

        .edit-btn,
        .remove-btn{
            padding: 10px 20px;
            color: #333;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: transform 0.3s ease;
        }

        .edit-btn:hover,
        .remove-btn:hover{
            background-color: #333;
            color: #fff;
            transform: scale(1.05);
        }
    </style>
    <script>
        function increment(productId, event) {
            event.preventDefault();
            var numberInput = document.getElementById("quantityInput" + productId);
            numberInput.value = parseInt(numberInput.value) + 1;
        }

        function decrement(productId, event) {
            event.preventDefault();
            var numberInput = document.getElementById("quantityInput" + productId);
            if (parseInt(numberInput.value) > 1) {
                numberInput.value = parseInt(numberInput.value) - 1;
            }
        }
        var totalBillElement = document.getElementById("total-bill");
        var totalBill = parseFloat(totalBillElement.textContent);
        if (isNaN(totalBill)) {
            totalBill = 0;
        }
        totalBillElement.textContent = "$" + totalBill.toFixed(2);
    </script>
</html>
