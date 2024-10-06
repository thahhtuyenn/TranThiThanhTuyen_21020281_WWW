package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.OrderService;

import java.util.List;

@Path("/orders")
public class OrderResource {
    @Inject
    private OrderService orderService;

    @GET
    public Response getAll() {
        List<OrderDto> orders = orderService.findAll();
        return Response.ok()
                .entity(orders)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        OrderDto order = orderService.findById(id);
        return Response.ok()
                .entity(order)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderDto order) {
        try{
            OrderDto newOrder = orderService.save(order);
            return Response.status(Response.Status.CREATED)
                    .entity(newOrder)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/employeeId={employeeId}")
    public Response getByEmployeeId(@PathParam("employeeId") Long employeeId) {
        List<OrderDto> orders = orderService.findByEmployee(employeeId);
        return Response.ok()
                .entity(orders)
                .build();
    }
}
