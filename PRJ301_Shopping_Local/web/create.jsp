
<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" type="text/css" href="/css/create.css">-->
        <title>Create User</title>
    </head>
    <body>
        <div class="container">
            <h1>Create new user</h1>
            <form class="login-form" action="MainController" method="POST">
                <div class="form-group">
                    <input type="email" name="email" value="${param.email}" required="" placeholder="Email"/>
                    <p class="error-message">${requestScope.USER_ERROR.emailError}</p>
                </div>
                <div class="form-group">
                    <input type="text" name="fullName" value="${param.fullName}" required="" placeholder="Full Name"/>
                    <p class="error-message">${requestScope.USER_ERROR.fullNameError}</p>
                </div>
                <div class="form-group">
                    <input type="text" name="address" value="${param.address}" required="" placeholder="Address"/>
                </div>
                <div class="form-group">
                    <input type="text" name="phone" value="${param.phone}" required="" placeholder="Phone"/>
                    <p class="error-message">${requestScope.USER_ERROR.phoneError}</p>
                </div>
                <div class="form-group">
                    <input type="password" name="password" value="" required="" placeholder="Password"/>
                </div>
                <div class="form-group">
                    <input type="password" name="confirm" value="" required="" placeholder="Confirm Password"/>
                    <p class="error-message">${requestScope.USER_ERROR.confirmError}</p>
                </div>
                
                <div class="recap-container">
                    <div class="g-recaptcha" data-sitekey="6Lf1ngEnAAAAAKRIXbq61q-9flqW5tcO8hN5a5cI"></div>
                </div>
                
                <div class="form-actions">
                    <input type="submit" name="action" value="Create" class="button">
                    <input type="reset" value="Reset" class="button">
                    <a href="MainController?action=" class="button-link">Back</a>
                </div>
            </form>
        </div>
        <p class="error-message" id="error-message">${requestScope.ERROR}</p>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function (){
                let isValid= false;
                const form = document.getElementById("form");
                const error = document.getElementById("error-message");
                
                form.addEventListener("submit", function (event){
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if(response){
                        form.submit();
                    } else {
                        error.innerHTML = "Please check the reCapcha first!";
                    }
                })
            }
        </script>
    </body>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
            margin-right: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="email"],
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .form-actions {
            margin-top: 20px;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            border-radius: 5px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #0056b3;
        }
        
        .button-link{
            display: inline-block;
            padding: 10px 20px;
            border-radius: 5px;
            background-color: #f53d2d;
            color: #ffffff;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;text-decoration: none;
            
        }
        
        .button-link:hover {
            background-color: #d02e2e;
        }

        .create-user-link {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
        }

        .error-message {
            margin-top: 20px;
            color: #dc3545;
        }

    </style>


</html>
