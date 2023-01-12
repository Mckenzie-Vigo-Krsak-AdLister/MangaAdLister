<%--
  Created by IntelliJ IDEA.
  User: aldanisvigo
  Date: 1/10/23
  Time: 2:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Recover</title>
        <jsp:include page="/partials/header.jsp" />
        <script src="/js/recover.js" defer></script>
        <style>
            .reset_password_form {
                text-align: center;
            }
            .reset_password_form label{
                display: grid;
                grid-template-columns: 1fr 1fr;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/partials/navbar.jsp" />
        <div class="container d-flex justify-content-center align-items-center">
            <div class="card p-4 mt-4" id="main_card">
                <div id="email_input_form">
                    <form action="/recover" method="post">
                        <p>
                            Please enter your email, and we will send you a password recovery link.
                        </p>
                        <input type="email" name="email" id="email" placeholder="Enter your email address"/>
                        <button type="submit">
                            Send Recovery Email
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
