package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.thanhtuyen.labweek1.Connect;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Log;
import vn.edu.iuh.fit.thanhtuyen.labweek1.repositories.LogRepository;

import java.time.Instant;
import java.util.List;

public class LogRepositoryImpl implements LogRepository {
    @Override
    public void saveTimeLogin(String accountId) {
        try(EntityManager em = Connect.getEntityManager()){
            em.getTransaction().begin();
            String querySelelct = "SELECT l FROM Log l WHERE l.accountId = :accountId ORDER BY l.id DESC LIMIT 1";
            List<Log> logs = em.createQuery(querySelelct, Log.class).setParameter("accountId", accountId).getResultList();
            Log log = logs.isEmpty() ? null : logs.get(0);
            //neu khong ton tai login nao cua account thi tao moi
            if(log == null){
                Log newLog = new Log();
                newLog.setAccountId(accountId);
                newLog.setLoginTime(Instant.now());
                newLog.setLogoutTime(null);
                newLog.setNotes(null);
                em.persist(newLog);
            }else {
                if (log.getLogoutTime() == null){
                    String queryUpdate = "UPDATE Log l SET l.logoutTime = CURRENT_TIMESTAMP WHERE l.id = :id";
                    em.createQuery(queryUpdate)
                            .setParameter("id", log.getId())
                            .executeUpdate();
                }else {
                    Log newLog = new Log();
                    newLog.setAccountId(accountId);
                    newLog.setLoginTime(Instant.now());
                    newLog.setLogoutTime(null);
                    newLog.setNotes(null);
                    em.persist(newLog);
                }
            }



            em.getTransaction().commit();
        }
    }

    @Override
    public void saveTimeLogout(String accountId) {
        try(EntityManager em = Connect.getEntityManager()){
            em.getTransaction().begin();
            String query = "SELECT l FROM Log l WHERE l.accountId = :accountId ORDER BY l.id DESC LIMIT 1";
            List<Log> logs = em.createQuery(query, Log.class).setParameter("accountId", accountId).getResultList();
            if(!logs.isEmpty()){
                Log log = logs.get(0);
                if(log.getLogoutTime() == null){
                    String queryUpdate = "UPDATE Log l SET l.logoutTime = CURRENT_TIMESTAMP WHERE l.id = :id";
                    em.createQuery(queryUpdate).setParameter("id", log.getId()).executeUpdate();
                }
            }
            em.getTransaction().commit();
        }
    }
}
