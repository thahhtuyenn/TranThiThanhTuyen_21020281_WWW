package vn.edu.iuh.fit.thanhtuyen.labweek1.repositories;

public interface LogRepository {
    void saveTimeLogin(String accountId);
    void saveTimeLogout(String accountId);
}
