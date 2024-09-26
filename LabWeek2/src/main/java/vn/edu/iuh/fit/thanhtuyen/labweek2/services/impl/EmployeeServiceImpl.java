package vn.edu.iuh.fit.thanhtuyen.labweek2.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.EmployeeStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.EmployeeRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.services.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        if (employee != null) {
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Employee> findByStatus(int status) {
        EmployeeStatus es = EmployeeStatus.ACTIVE;
        if (status == 0) es = EmployeeStatus.IN_ACTIVE;
        else if (status == -1) es = EmployeeStatus.TERMINATED;

        return employeeRepository.findByStatus(es);
    }
}
