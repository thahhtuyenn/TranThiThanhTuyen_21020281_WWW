package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Product;

@Mapper(uses = {ProductImageMapper.class, ProductPriceMapper.class})
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkProductImages(@MappingTarget Product product) {
        if(product.getProductImages() != null){
            product.getProductImages().forEach(productImage -> productImage.setProduct(product));
        }
    }

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