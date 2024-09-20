package vn.edu.iuh.fit.thanhtuyen.labweek2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import vn.edu.iuh.fit.thanhtuyen.labweek2.utils.AppUtils;

@ApplicationPath("/api")
public class AppApplication extends Application {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

}