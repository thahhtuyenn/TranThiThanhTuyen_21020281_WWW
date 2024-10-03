package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.entities.ProductPrice;

import java.util.List;

public interface ProductPriceRepository {
    /**
     * Lay ra danhh sach gia cua san pham theo id san pham
     * @param productId: id cua san pham
     * @return Danh sach gia cua san pham
     */
    List<ProductPrice> findByProductId(Long productId);

    /**
     * Lay ra gia cuoi cung cua san pham theo id san pham
     * @param productId: id cua san pham
     * @return Gia cuoi cung cua san pham
     */
    ProductPrice findLastPriceByProductId(Long productId);

    /**
     * Luu gia cua san pham
     * @param productPrice: Gia cua san pham
     * @return Gia cua san pham sau khi duoc luu
     * @return Gia cua san pham sau khi duoc luu
     */
    ProductPrice save(ProductPrice productPrice);
}
