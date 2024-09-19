<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 9/19/2024
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit user</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Edit user</h1>
    <form action="users" method="post">
        <c:if test="${not empty sessionScope.get('account')}">
            <input type="hidden" name="accountId" value="${account.accountId}">
            <input type="hidden" name="action" value="edit">
        </c:if>
        <c:if test="${empty sessionScope.get('account')}">
            <input type="hidden" name="action" value="add">
        </c:if>
        <div class="mb-3">
            <label for="fullName" class="form-label">Full name</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="${account.fullName}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${account.email}">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${account.phone}">
        </div>
        <div class="mb-3">
            <label for="roles" class="form-label">Roles</label>
            <ul id="roles" style="list-style: none" >
                <c:forEach var="role" items="${roles}">
                    <li>
                        <input type="checkbox" id="${role.getRoleId()}" name="roles" value="${role.getRoleId()}"
                               <c:if test="${account.isRole(role.getRoleId())}">checked</c:if>>
                        <label for="${role.getRoleId()}">${role.getRoleName()}</label>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
