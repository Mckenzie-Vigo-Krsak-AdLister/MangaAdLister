<%--
  Created by IntelliJ IDEA.
  User: john-michaelkrsak
  Date: 1/10/23
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.Manga" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Listing" %>
<%@ page import="models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Listing> listing = (List<Listing>) request.getAttribute("listing"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="/partials/header.jsp" />
    <script src="../js/profile.js" defer></script>
    <link rel="stylesheet" href="/css/profile.css"></link>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;300&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="/partials/navbar.jsp" />


<div id="backgroundImg"></div>
<div id="backgroundOverlay"></div>



<div class="container table">

    <button id="addListingBtn" class="mt-4 btn btn-light">Add new listing</button>

    <div id="createForm">
    </div>
    <div class="container ms-4 me-4">
        <div class="container">
            <div id="listingsContainer" class="container row mt-4">

            </div>
        </div>
    </div>
    <input type="hidden" id="userId" value="${loggedInUser.getId()}">

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>