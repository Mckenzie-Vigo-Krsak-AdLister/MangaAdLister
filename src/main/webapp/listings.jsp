<%@ page import="models.Manga" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="models.Listing" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Listing listing = (Listing) request.getAttribute("listing"); %>
<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<% User listingOwner = (User) request.getAttribute("listingOwner"); %>
<% List<Manga> mangas = (List<Manga>) request.getAttribute("mangas"); %>

<html>
    <head>
        <title>Manga Listings</title>
        <jsp:include page="partials/header.jsp" />

        <link href="./css/listings.css" rel="stylesheet" type="text/css"/>
        <script src="./js/profile.js" defer></script>
        <link href="${pageContext.request.contextPath}/css/listings.css" rel="stylesheet" type="text/css">
        <script type="module" defer>
            import { getCartSize } from '/js/cartsystem.js'
            (async () => await getCartSize())()
        </script>

        <link rel="stylesheet" href="/css/profile.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;300&display=swap" rel="stylesheet">

    </head>
    <body>
    <input id="backbuttonstate" type="text" value="0" style="display:none;" />
    <jsp:include page="partials/navbar.jsp" />
    <div id="backgroundImg"></div>
    <div id="backgroundOverlay"></div>
        <div class="container table">

            <h1 class="mt-4">Here Are all the ads!</h1>
            <div class="container ms-4 me-4">
                <div class="container">
                    <div id="search_view" class="listingsContainer row mt-4">

                    </div>
                </div>
            </div>
        </div>
    <input type="hidden" id="userId" value="${loggedInUser.getId()}">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
    </body>
</html>