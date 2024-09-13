<%--
  Created by IntelliJ IDEA.
  User: Thanh Tuyen
  Date: 9/13/2024
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>User</title>
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-light bg-light" style="padding-left: 20px; padding-right: 20px;">
            <c:if test="${not empty sessionScope.get('account')}">
                <p style='text-align: center'>
                    <c:out value="Welcome, ${sessionScope.get('account').getFullName()}"/>
                </p>
            </c:if>
            <c:if test="${empty sessionScope.get('account')}">
                <p style='text-align: center'>
                    <c:out value="Welcome, guest"/>
                </p>
            </c:if>

            <form action="login", method="post">
                <input type="hidden" name="action" value="logout"/>
                <button class="btn btn-secondary">Logout</button>
            </form>
        </nav>
        <c:if test="${ not empty sessionScope.get('account')}">
            <div>
                <h1 style="text-align: center">User Information</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Account ID</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${sessionScope.get('account').getAccountId()}</td>
                            <td>${sessionScope.get('account').getFullName()}</td>
                            <td>${sessionScope.get('account').getEmail()}</td>
                            <td>${sessionScope.get('account').getPhone()}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</body>
</html>
