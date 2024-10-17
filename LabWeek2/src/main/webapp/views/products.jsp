<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 10/3/2024
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Products</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/css/sb-admin-2.min.css"/>" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <div style="display: flex; align-items: center; justify-content: center">
            <h2 style="color: #fff; font-weight: bold">Products</h2>
        </div>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Order statistics</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Shop
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-cog"></i>
                <span>System</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="products">Products</a>
                </div>
            </div>
        </li>
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">${employee.fullName}</span>
                    </a>
                    <form action="login" method="post">
                        <input type="hidden" name="action" value="logout"/>
                        <button class="btn btn-secondary">Logout</button>
                    </form>

                </ul>
            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="row">
                            <c:forEach var="product" items="${products}" varStatus="status">
                            <!-- M?i item s? ???c ??t trong m?t c?t v?i l?p Bootstrap -->
                            <div class="col-md-3">
                                <div class="card mb-4">
                                    <div class="card-body"
                                         style="height: 400px;
                                         display: flex; flex-direction: column;
                                         background-color: #96dbe4; border-radius: 5px;
                                         justify-content: space-between; align-items: center;">
                                        <img src="${product.getImage()}" style="height: 150px; width: 100%;">
                                        <div style="height: 200px; width: 100%; align-items: center; justify-content: space-between;">
                                            <h5 style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${product.getName()}</h5>
                                            <span>Manufacturer: ${product.manufacturer}</span> <br>
                                            <span>Price: ${product.getPrice()}</span>
                                            <form action="products" method="get">
                                                <input type="hidden" name="action" value="addToCart"/>
                                                <input type="hidden" name="productId" value="${product.id}"/>
                                                <div class="mb-3">
                                                    <label for="quantity">Quantity: </label>
                                                    <input type="number" name="quantity" id="quantity" min="1" max="10"
                                                           value="1"
                                                           style="margin-bottom: 5px; width: 80px"/>
                                                </div>
                                                <button class="btn btn-primary">Add to cart</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Khi ?ã hi?n th? xong 4 c?t, t?o hàng m?i -->
                            <c:if test="${(status.index + 1) % 4 == 0}">
                        </div>
                        <div class="row">
                            </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-4" style="background-color: #96dbe4; border-radius: 8px">
                        <div class="row">
                            <div class="col-md-12"
                                 style="height: 100px; display: flex; justify-content: space-between; align-items: center">
                                <h3 style="color: #fff; text-align: center; font-weight: bold">Shopping cart</h3>
                                <a href="orders" type="button" class="btn btn-primary">Order</a>
                            </div>

                            <table class="table table-borderless">
                                <thead>
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Total</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="cartItem" items="${cart.cartDetails}">
                                    <tr>
                                        <td>${cartItem.product.name}</td>
                                        <td>${cartItem.quantity}</td>
                                        <td>${cartItem.price}</td>
                                        <td>${cartItem.getMoney()}</td>
                                        <td>
                                            <form action="products" method="get">
                                                <input type="hidden" name="action" value="removeFromCart"/>
                                                <input type="hidden" name="productId" value="${cartItem.product.id}"/>
                                                <button class="btn btn-danger">X</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>


                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Tran Thi Thanh Tuyen - 21020281 - thanhtuyen9623@gmail.com</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/js/sb-admin-2.min.js"/>"></script>

<!-- Page level plugins -->
<script src="<c:url value="/resources/vendor/chart.js/Chart.min.js"/>"></script>

<!-- Page level custom scripts -->
<script src="<c:url value="/resources/js/demo/chart-area-demo.js"/>"></script>
<script src="<c:url value="/resources/js/demo/chart-pie-demo.js"/>"></script>

</body>

</html>
