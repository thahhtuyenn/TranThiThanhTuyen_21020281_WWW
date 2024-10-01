package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Order;

@Mapper(uses = {OrderDetailMapper.class})
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void linkOrderDetails(@MappingTarget Order order) {
        if (order.getOrderDetails() != null) {
            order.getOrderDetails().forEach(orderDetail -> orderDetail.setOrder(order));
        }
    }

    OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}