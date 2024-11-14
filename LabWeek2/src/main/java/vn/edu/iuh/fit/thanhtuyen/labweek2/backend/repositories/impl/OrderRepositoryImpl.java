package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Order;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.OrderRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<Order> findAll() {
        return em.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.of(em.find(Order.class, id));
    }

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            order = em.merge(order);
        }
        return order;
    }

    @Override
    public List<Order> findByEmployeeId(Long employeeId) {
        return em.createNamedQuery("Order.findByEmployee", Order.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }
}
