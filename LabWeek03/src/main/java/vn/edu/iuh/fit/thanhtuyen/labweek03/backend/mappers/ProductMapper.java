package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.entities.Product;

@Mapper(uses = {ProductPriceMapper.class})
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkProductPrices(@MappingTarget Product product) {
        if (product.getProductPrices() != null) {
            product.getProductPrices().forEach(productPrice -> productPrice.setProduct(product));
        }
    }

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}