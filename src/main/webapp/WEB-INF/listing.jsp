
<%--
  Created by IntelliJ IDEA.
  User: alvinmckenzie
  Date: 1/8/23
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.Manga" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Listing" %>
<%@ page import="models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Listing listing = (Listing) request.getAttribute("listing"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<% User listingOwner = (User) request.getAttribute("listingOwner"); %>
<% List<Manga> mangaList = (List<Manga>) request.getAttribute("mangaList"); %>

<html>
<head>
    <title>Manga Listings</title>
    <jsp:include page="/partials/header.jsp" />

    <link rel="stylesheet" href="../css/listing.css">
    <script src="../js/profile.js" defer></script>
    <script type="module" defer>
        import {getCartSize} from '../js/cartsystem.js'
        window.addEventListener("pageshow", getCartSize, false);
    </script>

    <link rel="stylesheet" href="/css/profile.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;300&display=swap" rel="stylesheet">

</head>
<body>
<jsp:include page="/partials/navbar.jsp" />
<div id="backgroundImg"></div>
<div id="backgroundOverlay"></div>
<div class="container">

    <h1 class="mt-4">Hey ${loggedInUser.getFirstName()}!, here's the manga you asked for!</h1>
    <h2 style="display: ${loggedInUser.getId() != listing.getUserId() ? "none" : "block"}">YOUR LISTING:</h2>
    <h2 style="display: ${loggedInUser.getId() != listing.getUserId() ? "block" : "none"}">Posted by ${owner.getFirstName()} ${owner.getLastName()}</h2>
    <div class="container-fluid row mb-5">
        <div class="col-6 col-md-4 col-lg-3">
            <h2>${listing.title}</h2>
            <img src="${listing.image}" class="img-fluid img-thumbnail">
            <p>${listing.description}</p>

            <button id="addToCartButton" class="addToCartButton" listing="${listing.getId()}" style="border: none; background: transparent;display:${loggedInUser.getId() != listing.getUserId() ? "block" : "none"};">
                <span class="material-symbols-outlined">
                    add_shopping_cart
                </span>
            </button>
        </div>
    </div>

    <jsp:include page="/partials/messages.jsp" >
        <jsp:param name="listingOwner" value="${listingOwner.getFirstName()}" />
    </jsp:include>
    <input type="hidden" id="userId" value="${loggedInUser.getId()}">

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>