<%--
  Created by IntelliJ IDEA.
  User: john-michaelkrsak
  Date: 1/8/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MangaLister - Register</title>
    <jsp:include page="partials/header.jsp" />
    <link href="/css/register.css" rel="stylesheet" type="text/css">
    <script src="Js/register.js"></script>
</head>
<body>
<jsp:include page="partials/navbar.jsp" />
<div class="container d-flex justify-content-center align-items-center w-100">
    <div class="card p-5" id="regCard">
        <form action="/register" method="POST">
            <p>
                <label for="email">
                    Email
                    <input class="form-control" type="text" placeholder="Email" id="email" name="email"/>
                </label>
            </p>

            <p>
                <label for="password">
                    Password
                    <input class="form-control" type="password" placeholder="Password" id="password" name="password"/>
                </label>
            </p>

            <p>
                <label for="confirm">
                    Confirm Password
                    <input class="form-control" type="password" placeholder="Confirm" id="confirm" name="confirm"/>
                </label>
            </p>

            <p>
                <label for="name">
                    First Name
                    <input class="form-control" type="name" placeholder="First Name" id="name" name="name"/>
                </label>
            </p>

            <p>
                <label for="lastName">
                    Last Name
                    <input class="form-control" type="lastName" placeholder="Last Name" id="lastName" name="lastName"/>
                </label>
            </p>

            <p>
                <label for="accountType">
                    Account Type
                    <select id="accountType" name="accountType">
                        <option value="user">User</option>
                        <option value="admin">Admin</option>
                    </select>
                </label>
            </p>

            <p class="d-flex justify-content-center">
                <button class="btn btn-outline-success me-3">Register</button>
            </p>
        </form>
        <p>
            <a href="/login"><button class="me-2 btn btn-outline-success">Back to Login</button></a>
        </p>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>
