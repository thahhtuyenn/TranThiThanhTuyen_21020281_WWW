
<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 9/13/2024
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Dashboard</title>
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-light bg-light" style="padding-left: 20px; padding-right: 20px;">
            <c:if test="${not empty sessionScope.get('account')}">
                <p style='text-align: center'>
                    <c:out value="Admin, ${sessionScope.get('account').getFullName()}"/>
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

        <c:if test="${not empty sessionScope.get('account')}">
            <a class="btn btn-primary" href="users?action=add">ADD</a>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Account ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="account" items="${accounts}">
                    <tr>
                        <td><c:out value="${account.getAccountId()}"/></td>
                        <td><c:out value="${account.getFullName()}"/></td>
                        <td><c:out value="${account.getEmail()}"/></td>
                        <td><c:out value="${account.getPhone()}"/></td>
                        <td>
                            <a class="btn btn-primary" href="users?action=edit&accountId=${ account.accountId }">Edit</a>
                            <a class="btn btn-warning" href="users?action=delete&accountId=${account.accountId}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div>

        </div>

    </div>
</body>
</html>
