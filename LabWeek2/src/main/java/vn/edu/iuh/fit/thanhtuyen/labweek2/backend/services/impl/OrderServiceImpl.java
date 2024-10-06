package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services.OrderService;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers.OrderDetailMapper;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers.OrderMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private OrderMapper orderMapper;
    @Inject
    private OrderDetailMapper orderDetailMapper;
    @Inject
    private OrderDetailRepository orderDetailRepository;
    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductPriceRepository productPriceRepository;
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> orderMapper.toDto(value)).orElse(null);
    }

    @Override
    public OrderDto save(OrderDto order) {
        //chuyen doi tu dto sang entity
        Order orderE = orderMapper.toEntity(order);

        //Kiem tra order neu ton tai thi cap nhat
        if (orderE.getId() != null) {
            Order oldOrder = orderRepository.findById(orderE.getId()).orElse(null);
            if (oldOrder == null) {
                orderE = orderMapper.partialUpdate(order, oldOrder);
            }
        }
        Customer customer = customerRepository.findById(order.getCustomer().getId()).orElse(null);
        Employee employee = employeeRepository.findById(order.getEmployee().getId()).orElse(null);
        if (customer != null) {
            orderE.setCustomer(customer);
        }
        if (employee != null) {
            orderE.setEmployee(employee);
        }

        //Set mac dinhcho ngay tao hoa don la ngay hien tai
        orderE.setOrderDate(LocalDateTime.now());
        //set mac dinh khi tao order la chuua co chi tiet don hang de tranh loi
        orderE.setOrderDetails(new ArrayList<>());
        orderE = orderRepository.save(orderE);
        Order orderNew = orderE;
        //Luu danh sach cac chi tiet don hang
        List<OrderDetail> orderDetails = order.getOrderDetails().stream().map(orderDetailDto -> {
            OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDto);
            Optional<Product> product = productRepository.findById(orderDetailDto.getProductId());
            if (product.isPresent()) {
                orderDetail.setProduct(product.get());
                ProductPrice productPrice = productPriceRepository.findLastPriceByProductId(product.get().getId());
                orderDetail.setPrice(productPrice.getPrice());
                orderDetail.setOrder(orderNew);
            }
            return orderDetail;
        }).collect(Collectors.toList());
        orderDetails = orderDetailRepository.saveAll(orderDetails);

        orderE.setOrderDetails(orderDetails);
        return orderMapper.toDto(orderE);
    }

    @Override
    public List<OrderDto> findByEmployee(Long employeeId) {
        List<Order> orders = orderRepository.findByEmployeeId(employeeId);
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
