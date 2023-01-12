<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: aldanisvigo
  Date: 1/12/23
  Time: 5:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Boolean loggedIn = (Boolean) request.getSession().getAttribute("loggedIn"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>

<html>
<head>
    <title>Manga Listings</title>
    <jsp:include page="/partials/header.jsp" />
    <script src="${pageContext.request.contextPath}../js/listing.js" defer></script>
<%--    <script type="module" defer>--%>
<%--        import {getCartSize} from '../js/cartsystem.js'--%>
<%--        window.addEventListener("pageshow", getCartSize, false);--%>
<%--    </script>--%>
    <script type="module">
        import {getCartSize} from '../js/cartsystem.js'
        window.addEventListener("pageshow", getCartSize, false);
    </script>
<%--    <script src="../js/cartsystem.js" defer></script>--%>
    <link rel="stylesheet" href="../css/cart.css"/>
</head>
<body>
    <jsp:include page="/partials/navbar.jsp" />
    <div class="container p-4">
        <p>
            ${loggedInUser.getEmail()}'s Shopping Cart you have <span id="cartSizeLabel"></span> items.
        </p>
        <div id="cartItems"></div>
    </div>
</body>
</html>
