<%--
  Created by IntelliJ IDEA.
  User: john-michaelkrsak
  Date: 1/10/23
  Time: 09:55
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
    <script src="./js/listing.js"></script>
</head>
<body>
<div class="message-wrapper">
    <div class="chat-box">
        <div class="moniker"><%= request.getParameter("listingOwner")%></div>
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>
