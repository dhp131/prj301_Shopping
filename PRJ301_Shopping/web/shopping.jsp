<%-- 
    Document   : shopping
    Created on : Jun 15, 2023, 10:14:57 AM
    Author     : ngohu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Huan's Store</title>

    </head>
    <body>
        <div class="nav-bar">
            <h1>Welcome ${sessionScope.LOGIN_USER.fullName}</h1>

            <div class="nav-bar-btn">
                <div class="view-cart">
                    <a class="btn" href="MainController?action=View">View Cart</a>
                </div>  
                <div class="view-infor">
                    <a class="btn" href="MainController?action=ViewInformation&userID=${sessionScope.LOGIN_USER.userID}">View Information</a>
                </div> 
                <div class="logout">
                    <a class="btn" href="MainController?action=Logout">Logout</a>
                </div>
            </div>
        </div>

        <div class="search-bar">
            <form action="MainController">
                <input class="search-label" type="text" name="searchProduct" value="${param.searchProduct}" placeholder="Search"/>
                <input class="search-btn" type="submit" value="Search Product" name="action" />
                <select class="category" name="category">
                    <option>All</option>
                    <option>Smart phone</option>
                    <option>Console game</option>
                    <option>Laptop</option>
                    <option>TV</option>
                    <option>Headphone</option>
                    <option>Camera</option>
                    <option>Smart watch</option>
                    <option>Household goods</option>
                    <option>Speaker</option>
                    <option>Tablet</option>
                </select>
            </form> 
        </div>


        <div class="message">
            <h1>${requestScope.MESSAGE}</h1>
        </div>
        
        <div class="products-container">  
            <c:if test="${requestScope.PRODUCTS != null}">
                <c:if test="${not empty requestScope.PRODUCTS}">
                    <c:forEach var="product" items="${requestScope.PRODUCTS}">
                        <div class="item-container" style="">
                            <form action="MainController" method="POST">
                                <div class="item-img">
                                    <img src="${product.image}" alt="${product.name}" style="width: 200px; height: 200px;">
                                </div>

                                <div class="item-name">
                                    <h3>${product.name}</h3>
                                </div>

                                <div class="item-price">
                                    <h2>${product.price}$</h2>
                                </div>

                                <div class="item-discription">
                                    <p class="item-discription-content">${product.description}</p>
                                </div>

                                <div class="quantity-choice">
                                    <label for="numberInput">Quantity:</label>
                                    <input class="quantity-label" name="quantity" type="number" id="quantityInput${product.id}" value="1" readonly=""/>

                                    <div class="quantity-btn">
                                        <button onclick="increment(${product.id}, event)">+</button>
                                        <button onclick="decrement(${product.id}, event)">-</button>
                                    </div>

                                    <input type="hidden" name="productID" value="${product.id}" />
                                    <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}" />
                                </div>

                                <div class="submit-btn">
                                    <input class="add-button" type="submit" value="Add" name="action" />
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </c:if>
            </c:if>  
        </div>
    </body>
    <style>
        body {
            margin: 0px;
            font-family: Arial, sans-serif;
        }

        .products-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .item-container {
            width: 250px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-align: center;
            background-color: #fff;
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
        }

        .item-description p {
            margin-top: 10px;
            font-size: 14px;
        }

        .item-discription-content{
            color: #7d7d7d;
            font-style: italic;
            font-weight: 100;
            font-size: smaller;
        }

        .nav-bar {
            background-color: #f2f2f2;
            padding: 20px;
            display: flex;
            justify-content: space-between;
        }

        .nav-bar h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 10px;
        }

        .nav-bar-btn {
            display: flex;
            align-items: center;
        }

        .nav-bar-btn div {
            margin-right: 10px;
        }

        .nav-bar-btn a {
            display: inline-block;
            padding: 10px;
            background-color: #333;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .nav-bar-btn a:hover {
            background-color: #555;
        }

        .nav-bar-btn a.btn {
            background-color: #333;
            margin-right: 10px;
            transition: background-color 0.3s ease;
        }

        .nav-bar-btn a.btn:hover {
            transform: scale(1.05);
        }


        .search-bar {
            display: flex;
            align-items: center;
            height: 80px;
            justify-content: center;
            background-color: #f2f2f2;
            margin-bottom: 16px;

        }

        .search-bar form {
            display: flex;
            align-items: center;
            background-color: #f2f2f2;
            border-radius: 5px;
            padding: 5px;
            width: 50%;
        }

        .search-label {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 50%;
        }

        .search-btn {
            padding: 8px 12px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 8px;
            transition: transform 0.3s ease;
        }

        .search-btn:hover{
            transform: scale(1.05);
        }

        .category {
            padding: 8px;
            border: none;
            background-color: #333;
            color: #fff;
            border-radius: 5px;
            margin-left: 10px;
            transition: transform 0.3s ease;
        }

        .category option {
            color: #fff;
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

        .add-button {
            margin-top: 8px;
            padding: 10px 20px;
            color: #333;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: transform 0.3s ease;
        }

        .add-button:hover{
            background-color: #333;
            color: #fff;
            transform: scale(1.05);
        }
        
        .message{
            text-align: center;
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
    </script>
</html>
