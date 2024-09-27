package vn.edu.iuh.fit.thanhtuyen.labweek2.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Customer;
import vn.edu.iuh.fit.thanhtuyen.labweek2.services.CustomerService;
import vn.edu.iuh.fit.thanhtuyen.labweek2.utils.AppUtils;

import java.util.List;

@Path("/customers")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    public Response getCustomers() {
        List<CustomerDto> customers = customerService.findAll();
        return Response.ok()
                .entity(customers)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        CustomerDto customer = customerService.findById(id);
        return Response.ok()
                .entity(customer)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CustomerDto customer) {
        try {
            customer = customerService.save(customer);
            return Response.status(Response.Status.CREATED)
                    .entity(customer)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(CustomerDto customer) {
        customer = customerService.save(customer);
        return Response.ok()
                .entity(customer)
                .build();

    }
}
