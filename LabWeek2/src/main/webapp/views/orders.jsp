<%--
  Created by IntelliJ IDEA.
  User: Admins
  Date: 10/4/2024
  Time: 10:08 PM
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

    <title>Order</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/css/sb-admin-2.min.css"/>" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->

        <div style="display: flex; align-items: center; justify-content: center">
            <h2 style="color: #fff; font-weight: bold">Orders</h2>
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
                    <div class="col-md-7">
                        <div style="width: 100%; background-color: #96dbe4; padding: 20px">
                            <h2>Order details</h2>
                            <table class="table table-borderless">
                                <thead>
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Money</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${cart.cartDetails}" var="cart">
                                    <tr>
                                        <td>${cart.product.name}</td>
                                        <td>${cart.quantity}</td>
                                        <td>${cart.price}</td>
                                        <td>${cart.getMoney()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div>
                            <h2>Create new order</h2>

                        </div>
                        <form action="orders" method="get" style="">
                            <input type="hidden" name="action" value="searchCustomer"/>
                            <div class="row">
                                <div class="col col-md-9">
                                    <label for="phone" class="form-label">Customer phone number:</label>
                                    <input type="text" class="form-control" id="phone"
                                           placeholder="Enter customer phone number"
                                           name="phone">
                                </div>
                                <button type="submit" class="col btn btn-primary">
                                    Search
                                </button>
                            </div>
                        </form>

                        <!-- Button to Open the Modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
                            New customer
                        </button>

                        <!-- The Modal -->
                        <div class="modal" id="myModal">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">New customer</h4>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <form action="orders" method="post">
                                            <input type="hidden" name="action" value="createCustomer"/>
                                            <div class="mb-3">
                                                <label for="name" class="form-label">Name:</label>
                                                <input type="text" class="form-control" id="name" name="name" placeholder="Enter customer name">
                                            </div>
                                            <div class="mb-3">
                                                <label for="phoneCustomer" class="form-label">Phone:</label>
                                                <input type="text" class="form-control" id="phoneCustomer" name="phoneCustomer" placeholder="Enter customer phone">
                                            </div>
                                            <div class="mb-3">
                                                <label for="address" class="form-label">Address:</label>
                                                <input type="text" class="form-control" id="address" name="address" placeholder="Enter customer address">
                                            </div>
                                            <div class="mb-3">
                                                <label for="email" class="form-label">Email:</label>
                                                <input type="email" class="form-control" id="email" name="email" placeholder="Enter customer email">
                                            </div>
                                            <button type="submit" class="btn btn-primary">Create</button>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <form action="orders" method="post">
                            <input type="hidden" name="action" value="createOrder" />
                            <div class="mb-3">
                                <c:if test="${customer == null}">
                                    <div class="alert alert-info" role="alert">
                                        Customer not found!
                                    </div>
                                </c:if>
                                <input type="hidden" value="${customer != null ? customer.id : null}" name="customerId" />
                                <label for="customerName" class="form-label">Customer name:</label>
                                <input type="text" class="form-control" id="customerName" name="customerName" contenteditable="false" value="${customer != null ? customer.name : ""}" />
                            </div>

                            <div class="mb-3">
                                <label for="totalMoney" class="form-label">Total money:</label>
                                <input type="text" class="form-control" id="totalMoney" name="totalMoney" value="${cart.getTotal()}" contenteditable="false" />
                            </div>

                            <button type="submit" class="btn btn-primary">Create</button>
                        </form>


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
