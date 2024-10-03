package vn.edu.iuh.fit.thanhtuyen.labweek03.frontend.models.impl;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.frontend.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductModelImpl implements ProductModel {
    private static final String API_URL = "http://localhost:8080/LabWeek03-1.0-SNAPSHOT";

    @Override
    public List<ProductDto> getProducts() {
        try (Client client = ClientBuilder.newClient()) {
            String url = "/api/products/statusAndPrice/status=ACTIVE";
            Response response = client.target(API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(new GenericType<>() {
                });
            } else {
                return new ArrayList<>();
            }
        }
    }

    @Override
    public ProductDto getById(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            String url = "/api/products/" + id;
            Response response = client.target(API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }
        }
    }

    @Override
    public ProductDto deleteById(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            ProductDto productDto = getById(id);
            String url = "api/products/updateStatus/id=" + id + "&status=TERMINATED"; //api/products/updateStatus/id=7&status=IN_ACTIVE
            Response response = client.target(API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.text(""));
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }

        }

    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        try (Client client = ClientBuilder.newClient()) {
            String url = "/api/products";
            Response response = client.target(API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(productDto));
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        try (Client client = ClientBuilder.newClient()) {
            String url = "/api/products";
            Response response = client.target(API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(productDto));
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }
        }
    }

    @Override
    public ProductDto setPrice(ProductPriceDto productPriceDto) {
        try(Client client = ClientBuilder.newClient()){
            String url = "/api/products/updatePrice/productId=" + productPriceDto.getProductId();
            Response response = client.target(API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(productPriceDto));
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }
        }
    }
}
