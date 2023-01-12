<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: aldanisvigo
  Date: 1/8/23
  Time: 4:41 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Boolean loggedIn = (Boolean) request.getSession().getAttribute("loggedIn"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>

<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <div>
            <img src="/img/logo.png" width="60px" height="60"/>
            <a class="navbar-brand col-2">Manga Lister</a>
        </div>
        <form class="d-flex justify-content-center col-12 col-md-3" role="search">
            <input class="form-control me-2 mt-3" type="search" placeholder="Find Titles" aria-label="Find">
            <button class="btn btn-outline-success mt-3" type="submit">Search</button>
        </form>
        ${loggedInUser.getFirstName().toString()}
        <button style="border: none; background: transparent; display:${loggedIn ? "block" : "none"};" id="cartButton">
            <span id="cartSizeLabel"></span>
            <span class="material-symbols-outlined">
                shopping_cart
            </span>
        </button>
        <a href="/login" style="display:${!loggedIn ? "block" : "none"};">
            <button class="btn btn-outline-success">Login</button>
        </a>
        <form style="display:${loggedIn ? "block" : "none"};" action="/logout" method="post" class="mt-3">
            <button type="submit" style="border: none; background: transparent;">
               <span class="material-symbols-outlined">
                    logout
               </span>
            </button>
        </form>
    </div>
</nav>