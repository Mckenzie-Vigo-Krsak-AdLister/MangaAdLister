<%--
  Created by IntelliJ IDEA.
  User: john-michaelkrsak
  Date: 1/10/23
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>

<html>
<head>
    <title>Title</title>
    <script src="../js/messages.js"></script>
    <link rel="stylesheet" href="../css/listing.css">
</head>
<body>
<div class="message-wrapper">
    <div class="chat-box">
        <div class="moniker">Nana</div>
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
</body>
</html>
