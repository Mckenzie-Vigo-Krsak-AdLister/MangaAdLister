<%@ page import="models.Manga" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Manga> mangas = (List<Manga>) request.getAttribute("mangas"); %>

<html>
    <head>
        <title>Manga Listings</title>
        <jsp:include page="partials/header.jsp" />
        <link href="/css/listings.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <jsp:include page="partials/navbar.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-8">
                    <input type="text" placeholder="Search" id="searchListings" class="form-control"/>
                </div>
                <div class="col-2">
                    <select id="searchType" class="form-select">
                        <option value="title">Title</option>
                        <option value="author">Genre</option>
                    </select>
                </div>
                <div class="col-2">
                    <button id="searchButton" class="btn btn-primary">Search</button>
                </div>
            </div>
            <h1 class="mt-4">Here Are all the ads!</h1>
            <div class="container-fluid row">
                <c:forEach var="manga" items="${mangas}">
                    <div class="col-6 col-md-4 col-lg-3">
                        <h2>${manga.title}</h2>
                        <p>${manga.description}</p>
                        <a href="/listing?id=${manga.id}"><img src="${manga.image}" alt="${manga.title}" class="img-fluid img-thumbnail"></a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
    </body>
</html>