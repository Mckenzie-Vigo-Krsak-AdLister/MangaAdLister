<%--
  Created by IntelliJ IDEA.
  User: aldanisvigo
  Date: 1/8/23
  Time: 4:41 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand col-2">Manga Lister</a>
        <form class="d-flex justify-content-center col-12 col-md-3" role="search">
            <input class="form-control me-2 mt-3" type="search" placeholder="Find Titles" aria-label="Find">
            <button class="btn btn-outline-success mt-3" type="submit">Search</button>
        </form>
        <form style="display:${loggedIn ? "block" : "none"};" action="/logout" method="post">
            <button>Logout</button>
        </form>
        <a href="/login" style="display:${loggedIn ? "block" : "none"};">
            <button class="btn btn-outline-success mt-3"></button>
        </a>
    </div>
</nav>