<%--
  Created by IntelliJ IDEA.
  User: aldanisvigo
  Date: 1/8/23
  Time: 4:17 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MangaLister - Login</title>
    <jsp:include page="partials/header.jsp" />
</head>
<body>
  <jsp:include page="partials/navbar.jsp" />
  <div class="container d-flex justify-content-center align-items-center h-75 w-100">
    <div class="card p-5">
      <form action="/login" method="POST">
        <p>
          <label for="email">
            Email
            <input class="form-control" type="text" placeholder="Email" id="email" name="email"/>
          </label>
        </p>

        <p>
          <label for="password">
            Password
            <input class="form-control" type="password" placeholder="Password" id="password" name="password"/>
          </label>
        </p>

        <p class="d-flex justify-content-center">
          <button class="btn btn-outline-success me-3">Register</button>
          <button class="btn btn-outline-success">Login</button>
        </p>
        <p>
          <a href="/recover">
            Recover
          </a>
        </p>
      </form>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>
