package vn.edu.iuh.fit.thanhtuyen.labweek2.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Customer;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.CustomerRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return customerRepository.delete(id);
    }
}
