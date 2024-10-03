package vn.edu.iuh.fit.thanhtuyen.labweek03.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.enums.ProductStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek03.frontend.models.ProductModel;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    @EJB
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<ProductDto> products = productModel.getProducts();
        String action = req.getParameter("action");
        ProductDto product = new ProductDto();
        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                product = null;
                req.setAttribute("product", product);
                session.setAttribute("productEdit", product);
                req.getRequestDispatcher("views/editProduct.jsp").forward(req, resp);
            } else if (action.equalsIgnoreCase("edit")) {
                String idParam = req.getParameter("productId");
                Long id = Long.parseLong(idParam);
                product = productModel.getById(id);
                req.setAttribute("productEdit", product);
                session.setAttribute("productEdit", product);
                req.getRequestDispatcher("views/editProduct.jsp").forward(req, resp);
            } else if (action.equalsIgnoreCase("delete")) {
                String idParam = req.getParameter("productId");
                Long id = Long.parseLong(idParam);
                product = productModel.deleteById(id);
                products = productModel.getProducts();
            }

        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("views/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<ProductDto> products = null;
        String action = req.getParameter("action");
        ProductDto product = new ProductDto();
        ProductPriceDto productPriceDto = new ProductPriceDto();
        String name = req.getParameter("name");
        String manufacturer = req.getParameter("manufacturer");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String unit = req.getParameter("unit");
        if (action.equalsIgnoreCase("add")) {
            product.setName(name);
            product.setManufacturer(manufacturer);
            product.setDescription(description);
            product.setUnit(unit);
            product = productModel.addProduct(product);
            if (product.getId() != null) {
                productPriceDto.setPrice(Double.parseDouble(price));
                productPriceDto.setProductId(product.getId());
                productPriceDto.setNote("Initial price");
                product = productModel.setPrice(productPriceDto);
            }
            products = productModel.getProducts();
            req.setAttribute("products", products);
//            req.getRequestDispatcher("views/product.jsp").forward(req, resp);
            resp.sendRedirect("products");
        } else {
            if (action.equalsIgnoreCase("edit")) {
                String idParam = req.getParameter("productID");
                Long id = Long.parseLong(idParam);
                product = productModel.getById(id);
                product.setName(name);
                product.setManufacturer(manufacturer);
                product.setDescription(description);
                product.setUnit(unit);
                double priceCurrent = product.getPriceLatest();
                double priceUpdate = Double.parseDouble(price);
                product = productModel.updateProduct(product);
                if (priceCurrent != priceUpdate) {
                    productPriceDto.setPrice(priceUpdate);
                    productPriceDto.setProductId(product.getId());
                    productPriceDto.setNote("Update price");
                    product = productModel.setPrice(productPriceDto);
                }
                products = productModel.getProducts();
                req.setAttribute("products", products);
    //            req.getRequestDispatcher("views/product.jsp").forward(req, resp);
                resp.sendRedirect("products");
            }
        }
    }
}
