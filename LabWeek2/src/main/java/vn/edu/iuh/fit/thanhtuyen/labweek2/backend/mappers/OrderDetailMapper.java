package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDetailDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.OrderDetail;

@Mapper
public interface OrderDetailMapper {

    @Mapping(target = "id.orderId", source = "orderId")
    @Mapping(target = "id.productId", source = "productId")
    OrderDetail toEntity(OrderDetailDto orderDetailDto);


    @Mapping(target = "orderId", source = "id.orderId")
    @Mapping(target = "productId", source = "id.productId")
    OrderDetailDto toDto(OrderDetail orderDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetail partialUpdate(OrderDetailDto orderDetailDto, @MappingTarget OrderDetail orderDetail);
}