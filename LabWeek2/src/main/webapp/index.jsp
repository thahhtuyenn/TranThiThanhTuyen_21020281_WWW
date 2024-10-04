<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 9/27/2024
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container" style=" margin-top: 100px ">
    <h1>Login with phone number</h1>

    <form action="login" method="post">
        <c:if test="${message != null}">
            <div class='alert alert-success' role='alert'>${message}</div>
        </c:if>
        <input type="hidden" value="login" name="action">
        <div class="mb-3">
            <label for="phone" class="form-label">Phone number: </label>
            <input type="text" class="form-control" id="phone" placeholder="Enter your phone number" name="phone" >
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
