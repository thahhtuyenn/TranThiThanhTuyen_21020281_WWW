package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.CustomerModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.utils.AppUtils;

import java.util.List;

@Stateless
public class CustomerModelImpl implements CustomerModel {
    @Override
    public List<CustomerDto> getCustomers() {
        String url = "/api/customers";
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<>(){});
            }else {
                return null;
            }
        }
    }

    @Override
    public CustomerDto getCustomerByPhone(String phone) {
        String url = "/api/customers/phone=" + phone;
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(CustomerDto.class);
            }else {
                return null;
            }
        }
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        String url = "/api/customers";
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(customer));
            if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
                return response.readEntity(CustomerDto.class);
            }else {
                return null;
            }
        }
    }
}
