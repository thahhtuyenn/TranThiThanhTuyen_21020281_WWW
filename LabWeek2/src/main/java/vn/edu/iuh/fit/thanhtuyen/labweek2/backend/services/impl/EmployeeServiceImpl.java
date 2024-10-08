package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers.EmployeeMapper;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(employeeMapper::toDto).orElse(null);
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        if(employee.getId() == null) {
            Employee oldEmployee = employeeRepository.findById(employee.getId()).orElse(null);
            if(oldEmployee != null) {
                employee = employeeMapper.partialUpdate(employeeDto, oldEmployee);
            }
        }
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> findByStatus(EmployeeStatus status) {

        return employeeRepository
                .findByStatus(status)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateStatus(Long id, EmployeeStatus status) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null) {
            employee = employeeRepository.updateStatus(id, status);
            return employeeMapper.toDto(employee);
        }
        return null;
    }

    @Override
    public EmployeeDto findByPhone(String phone) {
        Optional<Employee> eop = employeeRepository.findByPhone(phone);
        return eop.map(employeeMapper::toDto).orElse(null);
    }
}
