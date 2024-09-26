package vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.EmployeeStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.EmployeeRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.utils.AppUtils;

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
            Optional<Employee> e = findById(employee.getId());
            if (e.isPresent()) {
                Employee employeeUpdate = e.get();
                employeeUpdate.setFullName(employee.getFullName());
                employeeUpdate.setDob(employee.getDob());
                employeeUpdate.setAddress(employee.getAddress());
                employeeUpdate.setEmail(employee.getEmail());
                employeeUpdate.setPhone(employee.getPhone());
                employee = em.merge(employeeUpdate);
            }
        }

        return employee;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Employee> findByStatus(EmployeeStatus status) {
        return em.createNamedQuery("Employee.findByStatus", Employee.class)
                .setParameter("status", status)
                .getResultList();
    }
}
