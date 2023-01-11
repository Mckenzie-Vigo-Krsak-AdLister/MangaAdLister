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
<% Listing listing = (Listing) request.getAttribute("listing"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>

<html>
<head>
    <title>Profile</title>
    <jsp:include page="/partials/header.jsp" />
    <script src="../Js/messages.js"></script>
    <link rel="stylesheet" href="../css/listing.css">
</head>
<body>
<jsp:include page="/partials/navbar.jsp" />
<div class="container">
    <h1 class="mt-4">${loggedInUser.getFirstName()}'s Profile</h1>

    <form action="/profile" method="post">
        <label for="title">Manga Title
            <input name="title" id="title" type="text" placeholder="enter manga title">
        </label>
        <br>
        <label for="price">Set price at $
            <input name="price" id="price" type="text" placeholder="enter price here">
        </label>
        <br>
        <button type="submit">Submit Listing</button>
    </form>

    <div class="container-fluid row">
        <div class="col-6 col-md-4 col-lg-3">
            <c:forEach var="manga" items="${mangas}">
                <div class="col-6 col-md-4 col-lg-3">
                    <h2>${manga.title}</h2>
                    <a href="/listing?id=${manga.id}"><img src="${manga.image}" alt="${manga.title}" class="img-fluid img-thumbnail"></a>
                    <p>${manga.description}</p>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="/partials/messages.jsp" >
        <jsp:param name="listingOwner" value="${listingOwner.getFirstName()}" />
    </jsp:include>

    <div class="message-wrapper">
        <div class="chat-box">
            <div class="moniker">${listingOwner.getFirstName()}</div>
            <div class="dialog">
                <span class="para">Hello,Man!</span>
            </div>
            <div class="dialog">
                <span class="para">What are you up to</span>
            </div>
            <div class="dialog">
                <span class="para">Are you ok!!!</span>
            </div>
        </div>
        <form class="talking-form">
            <input type="text" class="talking-para" />
            <input type="submit" class="talking-btn" value="SEND" />
        </form>
        <div class="bubble-effect hide"></div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>