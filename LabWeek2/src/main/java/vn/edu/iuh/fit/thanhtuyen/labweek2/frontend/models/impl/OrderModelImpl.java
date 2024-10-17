package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDetailDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.CartDetail;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.OrderModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderModelImpl implements OrderModel {

    @Override
    public OrderDto createOrder(Cart cart) {
        try (Client client = ClientBuilder.newClient()) {
            OrderDto order = new OrderDto();
            if ((cart.getCartDetails() != null) || !cart.getCartDetails().isEmpty()){
                List<OrderDetailDto> orderDetails = new ArrayList<>();
                for(CartDetail c : cart.getCartDetails()){
                    OrderDetailDto orderDetail = new OrderDetailDto();
                    orderDetail.setProductId(c.getProduct().getId());
                    orderDetail.setQuantity(c.getQuantity());
                    orderDetail.setPrice(c.getPrice());
                    orderDetails.add(orderDetail);
                }
                order.setOrderDetails(orderDetails);
                order.setEmployee(cart.getEmployee());
                order.setCustomer(cart.getCustomer());
                String url = "/api/orders";
                Response response = client.target(AppUtils.API_URL)
                        .path(url)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(order));
                if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
                    return response.readEntity(OrderDto.class);
                }else {
                    return null;
                }
            }
            return null;
        }
    }


}
