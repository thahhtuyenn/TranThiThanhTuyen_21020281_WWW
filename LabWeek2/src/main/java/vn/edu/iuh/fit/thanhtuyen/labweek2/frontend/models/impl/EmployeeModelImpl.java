package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.EmployeeModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.utils.AppUtils;

@Stateless
public class EmployeeModelImpl implements EmployeeModel {
    @Override
    public EmployeeDto getEmployeeByPhone(String phone) {
        try (Client client = ClientBuilder.newClient()){
            String url = "/api/employees/searchEmployeeByPhone/phone=" + phone;
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(EmployeeDto.class);
            } else {
                return null;
            }
        }
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee) {
        try (Client client = ClientBuilder.newClient()){
            String url = "/updateEmployee";
            Response response = client.target(AppUtils.API_URL)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(employee));
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(EmployeeDto.class);
            } else {
                return null;
            }
        }
    }
}
