package vn.edu.iuh.fit.thanhtuyen.labweek03.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.dtos.ProductPriceDto;

import java.util.List;

@Remote
public interface ProductModel {

    /**
     * Lay danh sach san pham
     * @return danh sach san pham
     */
    List<ProductDto> getProducts();

    /**
     * Lay san pham theo id
     * @param id: id cua san pham
     * @return san pham
     */
    ProductDto getById(Long id);

    /**
     * Ham cap nhat trang thai san pham TERMINATED
     * @param id: id cua san pham
     * @return san pham sau khi cap nhat
     */
    ProductDto deleteById(Long id);

    /**
     * Ham them san pham
     * @param productDto: san pham can them
     * @return san pham sau khi them
     */
    ProductDto addProduct(ProductDto productDto);

    /**
     * Ham cap nhat san pham
     * @param productDto: san pham can cap nhat
     * @return san pham sau khi cap nhat
     */
    ProductDto updateProduct(ProductDto productDto);

    /**
     * Ham cap nhat gia san pham
     * @param productPriceDto: gia san pham can cap nhat
     * @return san pham sau khi cap nhat
     */
    ProductDto setPrice(ProductPriceDto productPriceDto);
}
