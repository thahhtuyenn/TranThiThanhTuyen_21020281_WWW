package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.resource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.utils.AppUtils;

@ApplicationPath("/api")
public class MyApplication extends Application {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
}
