package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;

import java.util.List;

@Remote
public interface ProductModel {
    /**
     * Lay danh sach product co trang thai la active
     * @return danh sach product
     */
    List<ProductDto> getProducts();

    /**
     * Lay product theo id
     * @param id id cua product
     * @return product
     */
    ProductDto getProductById(Long id);


}
