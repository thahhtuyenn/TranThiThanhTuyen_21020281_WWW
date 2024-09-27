package vn.edu.iuh.fit.thanhtuyen.labweek2.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository {
    /**
     * Tim kiem danh sach anh cua san pham
     * @param productId : id cua san pham
     * @return : danh sach anh cua san pham tuong ung
     */
    List<ProductImage> findByProductId(Long productId);

    /**
     * Tim kiem anh cua san pham
     * @param id : id cua anh
     * @return : anh cua san pham tuong ung
     */
    Optional<ProductImage> findById(Long id);

    /**
     * Them anh cua san pham
     * - Neu id anh da ton tai thi cap nhat lai anh
     * - Neu id anh chua ton tai thi them anh moi
     * @param productImage : thong tin anh cua san pham
     * @return : anh cua san pham duoc them
     */
    ProductImage save(ProductImage productImage);
}
