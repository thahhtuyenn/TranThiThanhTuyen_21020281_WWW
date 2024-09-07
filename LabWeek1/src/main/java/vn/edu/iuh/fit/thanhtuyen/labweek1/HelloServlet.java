package vn.edu.iuh.fit.thanhtuyen.labweek1;

import java.io.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        EntityManager entityManager = Persistence.createEntityManagerFactory("LabWeek1 MariaDB")
                .createEntityManager();
        entityManager.close();
    }

    public void destroy() {
    }
}