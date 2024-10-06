package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.OrderModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.utils.AppUtils;

import java.util.List;

@Stateless
public class OrderModelImpl implements OrderModel {
    @Override
    public List<OrderDto> getOrders(Long employeeId) {
        try (Client client = ClientBuilder.newClient()) {
            String url = "/api/orders/employeeId=" + employeeId;
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(new GenericType<>() {
                });
            } else {
                return null;
            }
        }
    }

    @Override
    public OrderDto createOrder(OrderDto order) {
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(AppUtils.API_URL)
                    .path("/api/orders")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(order));

            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                return response.readEntity(OrderDto.class);
            } else {
                return null;
            }
        }
    }
}
