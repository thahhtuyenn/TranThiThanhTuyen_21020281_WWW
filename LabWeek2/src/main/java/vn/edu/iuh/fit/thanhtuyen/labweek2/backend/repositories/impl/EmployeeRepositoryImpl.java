package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.util.List;
import java.util.Optional;

@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.of(em.find(Employee.class, id));
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == 0) {
            employee.setStatus(EmployeeStatus.ACTIVE);
            em.persist(employee);
        } else {
            em.merge(employee);
        }

        return employee;
    }

    @Override
    public Employee updateStatus(Long id, EmployeeStatus status) {
        Optional<Employee> employee = findById(id);
        if (employee.isPresent()) {
            Employee e = employee.get();
            e.setStatus(status);
            em.merge(e);
            return e;
        }
        return null;
    }

    @Override
    public List<Employee> findByStatus(EmployeeStatus status) {
        return em.createNamedQuery("Employee.findByStatus", Employee.class)
                .setParameter("status", status)
                .getResultList();
    }
}
