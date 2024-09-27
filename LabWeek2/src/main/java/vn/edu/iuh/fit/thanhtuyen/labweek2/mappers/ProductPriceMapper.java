package vn.edu.iuh.fit.thanhtuyen.labweek2.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPrice;

@Mapper(uses = ProductMapper.class)
public interface ProductPriceMapper {
    @Mapping(target = "productId", source = "id.productId")
    @Mapping(target = "priceDateTime", source = "id.priceDateTime")
    ProductPriceDto toDto(ProductPrice entity);

    @Mapping(target = "id.productId", source = "productId")
    @Mapping(target = "id.priceDateTime", source = "priceDateTime")
    @Mapping(target = "product", ignore = true)
    ProductPrice toEntity(ProductPriceDto dto);
}