package com.iuh.student.thanhtuyen.thuchanh_tuan03.api;

import com.iuh.student.thanhtuyen.thuchanh_tuan03.business.BaseProcess;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/students")
public class StudentResource {

    @Inject
    private BaseProcess baseProcess;

    @GET
    public Response getStudents() {
        Response.ResponseBuilder response = Response.ok();
        response.entity(baseProcess.findAll());
        return response.build();
    }

    @GET
    @Path("/{id}")
    public Response getStudentById(@PathParam("id") int id) {
        Response.ResponseBuilder response = Response.ok();
        response.entity(baseProcess.findById(id));
        return response.build();
    }
}
