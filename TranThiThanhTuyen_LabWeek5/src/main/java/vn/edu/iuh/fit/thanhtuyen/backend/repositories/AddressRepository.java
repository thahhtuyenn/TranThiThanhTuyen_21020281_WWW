package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT DISTINCT a.city FROM Address a")
    List<String> findDistinctCity();
}
