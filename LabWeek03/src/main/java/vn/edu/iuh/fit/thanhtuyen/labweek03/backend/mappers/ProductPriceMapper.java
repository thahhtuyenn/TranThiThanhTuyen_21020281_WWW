package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.entities.ProductPrice;

@Mapper(uses = {ProductMapper.class})
public interface ProductPriceMapper {
    @Mapping(target = "productId", source = "id.productId")
    @Mapping(target = "priceDateTime", source = "id.priceDateTime")
    ProductPriceDto toDto(ProductPrice entity);

    @Mapping(target = "id.productId", source = "productId")
    @Mapping(target = "id.priceDateTime", source = "priceDateTime")
    @Mapping(target = "product", ignore = true)
    ProductPrice toEntity(ProductPriceDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductPrice partialUpdate(ProductPriceDto productPriceDto, @MappingTarget ProductPrice productPrice);
}