package vn.edu.iuh.fit.thanhtuyen.labweek2.resources;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.EmployeeStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.services.EmployeeService;

import java.util.List;

@Path("/employees")
public class EmployeeResource {
    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getEmployees() {
        List<Employee> employees = employeeService.findAll();
        return Response.ok()
                .entity(employees)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        Employee employee = employeeService.findById(id);
        return Response.ok()
                .entity(employee)
                .build();
    }

    @GET
    @Path("/status/{status}")
    public Response getEmployeeByStatus(@PathParam("status") int status) {
        List<Employee> employees = employeeService.findByStatus(status);
        return Response.ok()
                .entity(employees)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Employee employee) {
        try {
            employee = employeeService.save(employee);
            return Response.status(Response.Status.CREATED)
                    .entity(employee)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Server error")
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Employee employee) {
        employee = employeeService.save(employee);
        return Response.ok()
                .entity(employee)
                .build();
    }
}
