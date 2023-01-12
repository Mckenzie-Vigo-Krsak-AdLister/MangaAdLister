<%--
  Created by IntelliJ IDEA.
  User: aldanisvigo
  Date: 1/8/23
  Time: 4:41 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="../Js/search.js" defer></script>
</head>
<body>
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand col-2">Manga Lister</a>
        <input class="form-control me-2 mt-3" type="search" placeholder="Find Titles" aria-label="Find" id="searchTerm" name="searchTerm">
<%--        <button class="btn btn-outline-success mt-3" type="submit">Search</button>--%>
        <form style="display:${loggedIn ? "block" : "none"};" action="/logout" method="post">
            <button class="btn btn-outline-danger">Logout</button>
        </form>
        <a href="/login" style="display:${!loggedIn ? "block" : "none"};">
            <button class="btn btn-outline-success">Login</button>
        </a>
    </div>

</nav>
</body>
</html>