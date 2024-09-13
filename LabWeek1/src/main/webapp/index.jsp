<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <c:if test="${error} != null">
            <div class='alert alert-danger' role='alert'>${error}</div>
        </c:if>

        <c:if test="${message} != null">
            <div class='alert alert-success' role='alert'>${message}</div>
        </c:if>
        <form action="login" method="post">
            <input type="hidden" value="login" name="action">
            <div class="mb-3 mt-3">
                <label for="account" class="form-label">Account:</label>
                <input type="text" class="form-control" id="account" placeholder="Enter account" name="account">
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</body>
</html>