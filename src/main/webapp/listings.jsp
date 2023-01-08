<%@ page import="models.Manga" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Manga> mangas = (List<Manga>) request.getAttribute("mangas"); %>

<html>
    <head>
        <title>Manga Listings</title>
    </head>
    <body>
        <div class="container">
            <h1>Here Are all the ads!</h1>
            <c:forEach var="manga" items="${mangas}">
                <div class="col-md-6">
                    <h2>${manga.title}</h2>
                    <p>${manga.description}</p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>