package vn.edu.iuh.fit.thanhtuyen.labweek03.backend.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.entities.Product;
import vn.edu.iuh.fit.thanhtuyen.labweek03.backend.enums.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    /**
     * Tìm tất cả sản phẩm
     *
     * @return List<Product> : tra ve danh sach tat ca san pham
     */
    List<Product> findAll();

    /**
     * Tìm sản phẩm theo id
     *
     * @param id : id cua san pham
     * @return Optional<Product> : tra ve san pham co id tuong ung
     */
    Optional<Product> findById(Long id);

    /**
     * Lưu sản phẩm
     * - Nếu sản phẩm chưa có id thì thực hiện persist (them moi)
     * - Nếu sản phẩm đã có id thì thực hiện merge (cao nhat)
     *
     * @param product : san pham can luu
     * @return Product : tra ve san pham da duoc luu
     */
    Product save(Product product);

    /**
     * Cap nhat trang thai san pham
     * - Nếu tim thay san pham theo id thì cap nhat trang thai san pham
     * - Nếu không tim thay san pham theo id thì tra ve null
     *
     * @param id     : id cua san pham
     * @param status : trang thai san pham ACTIVE(1) hoac IN_ACTIVE(0) hoac TERMINATED(-1)
     * @return san pham da duoc cap nhat trang thai
     */
    Product updateStatus(Long id, ProductStatus status);

    /**
     * Tìm sản phẩm theo nhà sản xuất
     *
     * @param manufacturer : ten nha san xuat
     * @return List<Product> : tra ve danh sach san pham cua nha san xuat
     */
    List<Product> findByManufacturer(String manufacturer);

    /**
     * Tìm sản phẩm theo tên
     *
     * @param name : ten san pham
     * @return List<Product> : tra ve danh sach san pham co ten tuong ung
     */
    List<Product> findByName(String name);


    /**
     * Tìm sản phẩm theo trạng thái
     *
     * @param status : trạng thái sản phẩm ACTIVE(1) hoặc IN_ACTIVE(0) hoặc TERMINATED(-1)
     * @return List<Product> : tra ve danh sach san pham co trang thai tuong ung
     */
    List<Product> findByStatus(ProductStatus status);
}
