package vn.edu.iuh.fit.thanhtuyen.labweek2.resources;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.EmployeeDto;
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
        List<EmployeeDto> employees = employeeService.findAll();
        return Response.ok()
                .entity(employees)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        EmployeeDto employee = employeeService.findById(id);
        return Response.ok()
                .entity(employee)
                .build();
    }

    @GET
    @Path("/status/{sta}")
    public Response getEmployeeByStatus(@PathParam("sta") String status) {
        List<EmployeeDto> employees = employeeService.findByStatus(EmployeeStatus.valueOf(status));
        return Response.ok()
                .entity(employees)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EmployeeDto employee) {
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
    public Response update(EmployeeDto employee) {
        employee = employeeService.save(employee);
        return Response.ok()
                .entity(employee)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateStatus/id={id}&status={sta}")
    public Response updateSatus(@PathParam("id") Long id, @PathParam("sta") String status) {
        try {
            EmployeeDto employee = employeeService.updateStatus(id, EmployeeStatus.valueOf(status));
            if (employee == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee not found")
                        .build();
            }
            return Response.ok()
                    .entity(employee)
                    .build();
        }catch (Exception e) {
            return Response.serverError()
                    .entity("Server error")
                    .build();
        }
    }

}
