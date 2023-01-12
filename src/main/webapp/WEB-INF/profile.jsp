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
<%--<jsp:useBean id="listing" scope="request" type="models.Listing"/>--%>
<% List<Listing> listing = (List<Listing>) request.getAttribute("listing"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>

<html>
<head>
    <title>Profile</title>
    <jsp:include page="/partials/header.jsp" />
<%--    <script src="/Js/messages.js"></script>--%>
    <script src="../js/profile.js" defer></script>
<%--    <link rel="stylesheet" href="../css/listing.css">--%>
    <ink rel="stylesheet" href="../css/profile.css"></ink>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="/partials/navbar.jsp" />
<div class="header">
    <div class='grid-face' id="bgContainer">

    </div>



<div class="container table">
    <h1 class="mt-4">${loggedInUser.getFirstName()}'s Profile</h1>

    <button id="addListingBtn" class="btn btn-light">Add new listing</button>

    <div id="createForm">
    </div>

    <div class="container">
<%--        <h1 class="mt-4">Here Are all the ads!</h1>--%>
        <div id="listingsContainer" class="container-fluid row mt-4">
            <c:forEach var="manga" items="${listing}">
                <div class="col-12 col-md-6 col-lg-4 mb-3">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between">
                            <h2>${manga.title}</h2>
                            <button listing="${manga.id}" id="deleteButton" class="deleteButton btn">x</button>
                        </div>
                        <div class="card-body d-flex flex-column align-items-center">
                            <div class="d-flex justify-content-between align-items-start">
                                <p class="fw-bold">Buy this manga for $${manga.price}0</p>
                                <button id="addToCartButton" class="btn"><i class="bi bi-cart-plus"></i></button>
                            </div>
                            <a href="/listing?id=${manga.id}"><img src="${manga.image}" alt="${manga.title}" class="img-fluid img-thumbnail mb-2"></a>
                            <p>${manga.description}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <input type="hidden" id="userId" value="${loggedInUser.getId()}">

<%--    <jsp:include page="/partials/messages.jsp" >--%>
<%--        <jsp:param name="listingOwner" value="${listingOwner.getFirstName()}" />--%>
<%--    </jsp:include>--%>

<%--    <div class="message-wrapper">--%>
<%--        <div class="chat-box">--%>
<%--            <div class="moniker">${listingOwner.getFirstName()}</div>--%>
<%--            <div class="dialog">--%>
<%--                <span class="para">Hello,Man!</span>--%>
<%--            </div>--%>
<%--            <div class="dialog">--%>
<%--                <span class="para">What are you up to</span>--%>
<%--            </div>--%>
<%--            <div class="dialog">--%>
<%--                <span class="para">Are you ok!!!</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <form class="talking-form">--%>
<%--            <input type="text" class="talking-para" />--%>
<%--            <input type="submit" class="talking-btn" value="SEND" />--%>
<%--        </form>--%>
<%--        <div class="bubble-effect hide"></div>--%>
<%--    </div>--%>

</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>