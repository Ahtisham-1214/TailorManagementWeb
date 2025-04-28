<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/20/2025
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f3f4f6;
            font-family: Arial, sans-serif;
            position: relative;
            overflow: hidden;
        }

        .form-container {
            width: 320px;
            border-radius: 0.75rem;
            background-color: rgba(17, 24, 39, 1);
            padding: 2rem;
            color: rgba(243, 244, 246, 1);
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            position: relative;
            z-index: 10;
        }

        .title {
            text-align: center;
            font-size: 1.5rem;
            line-height: 2rem;
            font-weight: 700;
            margin-bottom: 1.5rem;
        }

        .form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .input-group {
            width: 100%;
            margin-bottom: 1rem;
            text-align: center;
        }

        .input-group label {
            display: block;
            color: rgba(156, 163, 175, 1);
            margin-bottom: 0.5rem;
            text-align: center;
            font-size: 0.875rem;
        }

        .input-group input {
            width: 90%;
            border-radius: 0.375rem;
            border: 1px solid rgba(55, 65, 81, 1);
            outline: 0;
            background-color: rgba(17, 24, 39, 1);
            padding: 0.75rem 1rem;
            color: rgba(243, 244, 246, 1);
            margin: 0 auto;
            display: block;
        }

        .input-group input:focus {
            border-color: rgba(167, 139, 250);
        }

        .forgot {
            text-align: center;
            font-size: 0.75rem;
            color: rgba(156, 163, 175, 1);
            margin: 0.5rem 0 1rem;
        }

        .forgot a,
        .signup a {
            color: rgba(243, 244, 246, 1);
            text-decoration: none;
            font-size: 0.75rem;
        }

        .forgot a:hover,
        .signup a:hover {
            text-decoration: underline rgba(167, 139, 250, 1);
        }

        .sign {
            width: 100%;
            background-color: rgba(167, 139, 250, 1);
            padding: 0.75rem;
            text-align: center;
            color: rgba(17, 24, 39, 1);
            border: none;
            border-radius: 0.375rem;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 0.5rem;
        }

        .sign:hover {
            background-color: rgba(156, 120, 240, 1);
        }

        .signup {
            text-align: center;
            font-size: 0.75rem;
            color: rgba(156, 163, 175, 1);
            margin-top: 1rem;
        }

        /* Blob animation styles */
        .blob {
            position: absolute;
            z-index: 1;
            width: 300px;
            height: 300px;
            border-radius: 50%;
            background-color: rgba(167, 139, 250, 0.6);
            opacity: 0.8;
            filter: blur(40px);
            animation: blob-bounce 15s infinite ease;
        }

        @keyframes blob-bounce {
            0% {
                transform: translate(-100%, -100%) translate3d(0, 0, 0);
            }

            25% {
                transform: translate(-100%, -100%) translate3d(100vw, 0, 0);
            }

            50% {
                transform: translate(-100%, -100%) translate3d(100vw, 100vh, 0);
            }

            75% {
                transform: translate(-100%, -100%) translate3d(0, 100vh, 0);
            }

            100% {
                transform: translate(-100%, -100%) translate3d(0, 0, 0);
            }
        }
    </style>
    <script>
        function validateForm(event) {
            event.preventDefault();
            const userNameField = document.getElementById("username").value.trim();
            const passwordField = document.getElementById("password").value.trim();
            const message = document.getElementById("message");
            if (message) {
                message.style.display = "none";
                message.innerText = "";
            }

            if (!userNameField){
                if (message){
                    message.textContent = "Username is Required";
                    message.style.display = "block";

                }
                userNameField.focus();
                return false;
            }

            if (!passwordField){
                if (message){
                    message.textContent = "Password is Required";
                    message.style.display = "block";

                }
                passwordField.focus();
                return false;
            }

            document.getElementById("loginForm").submit();
        }
    </script>
</head>

<body>
<!-- Animated blobs -->
<div class="blob" style="animation-delay: 0s;"></div>
<div class="blob" style="animation-delay: 5s; background-color: rgba(156, 120, 240, 0.6);"></div>
<div class="blob" style="animation-delay: 10s; background-color: rgba(156, 163, 175, 0.6);"></div>

<!-- Login Form -->
<div class="form-container">
    <p class="title">Login</p>

    <p id="message" style="color: #fca5a5; background-color: rgba(255, 0, 0, 0.1); text-align: center; padding: 0.75rem; border-radius: 0.375rem; margin-bottom: 1rem;
    ${empty message ? 'display:none;' : ''}">
        <c:out value="${message}" />
    </p>




    <form class="form" id="loginForm" action="LoginServlet" method="post" onsubmit="validateForm(event)">
        <div class="input-group">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="" required>
        </div>
        <div class="input-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="" required>
        </div>
        <div class="forgot">
            <a rel="noopener noreferrer" href="#">Forgot Password?</a>
        </div>
        <button type="submit" class="sign">Sign in</button>
    </form>
    <p class="signup">Don't have an account?
        <a rel="noopener noreferrer" href="#">Sign up</a>
    </p>
</div>
</body>

</html>
