
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" type="text/css" href="./css/login.css">-->
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>
            <form class="login-form" action="MainController?action=Login" method="POST" id="form">
                <div class="form-group">
                    <input type="text" name="loginUserID" id="loginUserID" required="" placeholder="Email or Full Name">
                </div>
                <div class="form-group">
                    <input type="password" name="password" id="password" required="" placeholder="Password">
                </div>

                <div class="seperate">
                    <p>or</p>
                </div>

                <div class="login-gg">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8084/PRJ301_T2S2_Assignment/LoginGoogle&response_type=code
                       &client_id=396845606449-ml3oh62oo3gfr6u7rb8l03a6fl9qk59c.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
                </div>

                <div class="recap-container">
                    <div class="g-recaptcha" data-sitekey="6Lf1ngEnAAAAAKRIXbq61q-9flqW5tcO8hN5a5cI"></div>
                </div>

                <div class="form-actions">
                    <input type="submit" name="action" value="Login" class="button">
                    <input type="reset" value="Reset" class="button">
                </div>
            </form>
            <a href="MainController?action=CreatePage" class="create-user-link">Create User</a>
            <p class="error-message" id="error-message">${requestScope.ERROR}</p>
            <p class="infor-message">${requestScope.MESSAGE}</p>
        </div>

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById("form");
                const error = document.getElementById("error-message");

                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if (response) {
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
            background-image: url(imgs/bg.jpg);
/*            background-size: cover;  Đảm bảo hình ảnh bao phủ toàn bộ khu vực nền */
/*            background-repeat: no-repeat;  Ngăn hình ảnh lặp lại */
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
        
        .infor-message{
            margin-top: 20px;
            color: #27cb2c;
        }
        
        .login-gg{
            margin-bottom: 16px;
        }
        .login-gg a{
            text-decoration: none;
            color: #fff;
            padding: 8px 16px;
            border-radius: 8px;
            background-color: #007bff;
            transition: transform 0.3s ease;
        }
        
        .login-gg:hover{
            transform: scale(1.05);
        }
    </style>

</html>
