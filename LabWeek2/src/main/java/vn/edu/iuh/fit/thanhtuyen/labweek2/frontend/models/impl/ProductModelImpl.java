package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ProductModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductModelImpl implements ProductModel {
    @Override
    public List<ProductDto> getProducts() {
        try(Client client = ClientBuilder.newClient()){
            String url = "/api/products/status=ACTIVE";
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<>(){});
            }else {
                return new ArrayList<>();
            }
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        String url = "/api/products/" + id;
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(ProductDto.class);
            }else {
                return null;
            }
        }
    }
}
