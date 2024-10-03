<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 10/2/2024
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
         pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

    <div class="container">
        <h1>Product List</h1>
        <a class="btn btn-primary" href="products?action=add">ADD</a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Manufacturer</th>
                <th>Unit</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.getPriceLatest()}</td>
                    <td>${product.manufacturer}</td>
                    <td>${product.unit}</td>
                    <td>
                        <a class="btn btn-primary" href="products?action=edit&productId=${ product.id }">Edit</a>
                        <a class="btn btn-warning" href="products?action=delete&productId=${ product.id }">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
