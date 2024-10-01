package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.ProductStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.ProductService;

import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService productService;

    @GET
    public Response getAllProducts() {
        List<ProductDto> products = productService.findAll();
        return Response.ok()
                .entity(products)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        ProductDto product = productService.findById(id);
        return Response.ok()
                .entity(product)
                .build();
    }

    @GET
    @Path("/manufacturer={manufacturer}")
    public Response getByManufacturer(@PathParam("manufacturer") String manufacturer) {
        List<ProductDto> products = productService.findByManufacturer(manufacturer);
        return Response.ok()
                .entity(products)
                .build();
    }

    @GET
    @Path("/productName={name}")
    public Response getByName(@PathParam("name") String name) {
        List<ProductDto> products = productService.findByName(name);
        return Response.ok()
                .entity(products)
                .build();
    }

    @GET
    @Path("/status={status}")
    public Response getByStatus(@PathParam("status") String status) {
        List<ProductDto> products = productService.findByStatus(ProductStatus.valueOf(status));
        return Response.ok()
                .entity(products)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProductDto product) {
        try {
            product = productService.save(product);
            return Response.status(Response.Status.CREATED)
                    .entity(product)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Server error")
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ProductDto product) {
        product = productService.save(product);
        return Response.ok()
                .entity(product)
                .build();
    }

    @PUT
    @Path("/updateStatus/id={id}&status={status}")
    public Response updateStatus(@PathParam("id") Long id, @PathParam("status") String status) {
        ProductDto product = productService.updateStatus(id, ProductStatus.valueOf(status));
        return Response.ok()
                .entity(product)
                .build();
    }

    @POST
    @Path("/updatePrice/productId={id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePrice(@PathParam("id") Long id, ProductPriceDto productPrice) {
        try {
            productPrice.setProductId(id);
            ProductDto product = productService.savePrice(productPrice);
            return Response.ok()
                    .entity(product)
                    .build();

        } catch (Exception e) {

            e.printStackTrace();
            return Response.serverError()
                    .entity("Server error")
                    .build();
        }
    }

    @GET
    @Path("/statusAndPrice/status={status}")
    public Response getProductsByStatusAndLastPrice(@PathParam("status") String status) {
        List<ProductDto> products = productService.findByStatusAndLastPrice(ProductStatus.valueOf(status));
        return Response.ok()
                .entity(products)
                .build();
    }


}
