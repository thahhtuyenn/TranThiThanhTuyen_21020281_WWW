package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.enums.ProductStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        if (productStatus == null) {
            return null;
        }
        return productStatus.getValue();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(ProductStatus.values())
                .filter(c -> c.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
