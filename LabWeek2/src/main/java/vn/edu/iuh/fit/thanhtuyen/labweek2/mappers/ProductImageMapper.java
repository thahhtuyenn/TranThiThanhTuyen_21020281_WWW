package vn.edu.iuh.fit.thanhtuyen.labweek2.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductImageDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductImage;

@Mapper
public interface ProductImageMapper {
    @Mapping(target = "productId", source = "product.id")
    ProductImageDto toDto(ProductImage entity);
    @Mapping(target = "product.id", source = "productId")
    ProductImage toEntity(ProductImageDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductImage partialUpdate(ProductImageDto productImageDto, @MappingTarget ProductImage productImage);
}