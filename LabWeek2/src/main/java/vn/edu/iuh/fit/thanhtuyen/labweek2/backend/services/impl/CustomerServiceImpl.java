package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Customer;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers.CustomerMapper;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private CustomerMapper customerMapper;


    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerMapper::toDto).orElse(null);
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        if (customerDto.getId() != null) {
            Customer oldCustomer = customerRepository.findById(customerDto.getId()).orElse(null);
            if (oldCustomer != null) {
                customer = customerMapper.partialUpdate(customerDto, oldCustomer);
            }
        }
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public boolean delete(Long id) {
        return customerRepository.delete(id);
    }
}
