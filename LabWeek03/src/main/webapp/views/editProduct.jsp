<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 10/2/2024
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
         pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>Edit product</h1>
        <form action="products" method="post">
            <c:if test="${not empty sessionScope.get('productEdit')}">
                <input type="hidden" name="action" value="edit">
                <div class="mb-3">
                    <label for="productID" class="form-label">Product ID</label>
                    <input type="text" contenteditable="false" class="form-control" id="productID" name="productID" value="${productEdit.id}" >
                </div>
            </c:if>
            <c:if test="${empty sessionScope.get('productEdit')}">
                <input type="hidden" name="action" value="add">

            </c:if>

            <div class="mb-3">
                <label for="name" class="form-label">Product name</label>
                <input type="text" class="form-control" id="name" name="name" value="${productEdit.name}">
            </div>
            <div class="mb-3">
                <label for="manufacturer" class="form-label">Manufacturer</label>
                <input type="text" class="form-control" id="manufacturer" name="manufacturer" value="${productEdit.manufacturer}">
            </div>
            <div class="mb-3">
                <label for="unit" class="form-label">Unit</label>
                <input type="text" class="form-control" id="unit" name="unit" value="${productEdit.unit}">
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" class="form-control" id="price" name="price" value="${productEdit.getPriceLatest()}">
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" rows="3" name="description">${productEdit.description}</textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
