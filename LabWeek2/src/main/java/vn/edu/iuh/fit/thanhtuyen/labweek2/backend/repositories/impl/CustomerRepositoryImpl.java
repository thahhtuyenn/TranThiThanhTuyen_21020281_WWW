package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Customer;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.util.List;
import java.util.Optional;

@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        return customers;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.of(em.find(Customer.class, id));
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            em.merge(customer);
        }
        return customer;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> c = findById(id);
        if (c.isPresent()) {
            em.remove(c.get());
            return true;
        }
        return false;
    }
}
