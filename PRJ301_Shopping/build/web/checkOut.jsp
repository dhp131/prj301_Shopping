<%-- 
    Document   : checkOut
    Created on : Jul 4, 2023, 10:29:27 AM
    Author     : ngohu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <div class="container">
            <div class="product-list">
                <!-- Product List -->
                <h2>Selected Products</h2>
                <ul>
                    <table border="1">
                        <tbody>
                            <c:set var="totalBill" value="0"/>
                            <c:forEach var="cart" items="${sessionScope.CART.cart}">
                                <tr>
                                    <td>${cart.value.product.name}</td>
                                    <td>X${cart.value.quantity}</td>
                                    <td>${cart.value.product.price}</td>
                                </tr>
                                <c:set var="totalBill" value="${totalBill + cart.value.product.price * cart.value.quantity}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="item-price">
                        <h2 id="total-bill">${totalBill}</h2>
                    </div>
                </ul>
            </div>
            <div class="confirmation-form">
                <!-- User Information -->
                <h2>Confirm Information</h2>
                <form action="MainController">
                    Email<input type="email" name="email" value="${sessionScope.LOGIN_USER.email}" required=""/></br>
                    Full Name<input type="text" name="fullName" value="${sessionScope.LOGIN_USER.fullName}" required=""/></br>
                    Phone<input type="text" name="phone" value="${sessionScope.LOGIN_USER.phone}" required=""/></br>
                    Shipping Address:<input type="text" name="address" value="${sessionScope.LOGIN_USER.address}" required=""><br>
                    <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}" />

                    <div class="submit-btn">
                        <input type="submit" value="Confirm" name="action">
                        <a class="btn" href="MainController?action=View">Back</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <style>
        body {
            margin: 20px;
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
        }

        .product-list {
            width: 50%;
            overflow: auto;
            padding-right: 20px; /* Add some spacing */
        }

        .confirmation-form {
            padding-left: 20px; /* Add some spacing */
        }

        .confirmation-form form {
            margin-top: 20px; /* Add some spacing */
        }

        .confirmation-form input[type="email"],
        .confirmation-form input[type="text"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        .confirmation-form input[type="submit"],
        .confirmation-form a.btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin: 0px 8px;
        }

        .confirmation-form input[type="submit"]:hover,
        .confirmation-form a.btn:hover {
            background-color: #555;
            transform: scale(1.05);
        }
        .product-list table {
            width: 100%;
            border-collapse: collapse;
        }

        .product-list th,
        .product-list td {
            padding: 8px;
            text-align: left;
        }

        .product-list th {
            background-color: #f2f2f2;
        }

        .product-list td {
            border-bottom: 1px solid #ddd;
        }

        .item-price {
            margin-top: 20px;
            display: flex;
            justify-content: flex-end;
        }

        .item-price h2 {
            font-size: 24px;
            color: #007bff;
            margin-right: 95px;
        }
        
        .submit-btn{
            display: flex;
            align-items: center;
        }

    </style>
    <script>
        var totalBillElement = document.getElementById("total-bill");
        var totalBill = parseFloat(totalBillElement.textContent);
        totalBillElement.textContent = "$" + totalBill.toFixed(2);
    </script>
</html>

