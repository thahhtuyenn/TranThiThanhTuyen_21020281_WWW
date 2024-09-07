package vn.edu.iuh.fit.thanhtuyen.labweek1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Connect {
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("LabWeek1 MariaDB").createEntityManager();
    }
}
