
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
<%--<jsp:useBean id="listing" scope="request" type="models.Listing"/>--%>
<% Listing listing = (Listing) request.getAttribute("listing"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<% User listingOwner = (User) request.getAttribute("listingOwner"); %>

<html>
<head>
    <title>Manga Listings</title>
    <jsp:include page="/partials/header.jsp" />
    <script src="../Js/listing.js"></script>
    <link rel="stylesheet" href="../css/listing.css">
</head>
<body>
<jsp:include page="/partials/navbar.jsp" />
<div class="container">
    <h1 class="mt-4">Hey ${loggedInUser.getFirstName()}!, here's the manga you asked for!</h1>
    <div class="container-fluid row">
        <div class="col-6 col-md-4 col-lg-3">
            <h2>${listing.title}</h2>
            <img src="${listing.image}" class="img-fluid img-thumbnail">
            <p>${listing.description}</p>
            <button id="sendSellerMessage">Message Seller</button>
        </div>
    </div>

    <jsp:include page="/partials/messages.jsp" >
        <jsp:param name="listingOwner" value="${listingOwner.getFirstName()}" />
    </jsp:include>

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>