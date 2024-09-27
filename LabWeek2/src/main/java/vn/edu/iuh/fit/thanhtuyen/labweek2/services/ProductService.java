package vn.edu.iuh.fit.thanhtuyen.labweek2.services;

import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductImageDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Product;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductImage;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPrice;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /** Tìm tất cả sản phẩm
     * @return List<Product> : tra ve danh sach tat ca san pham
     */
    List<ProductDto> findAll();

    /** Tìm sản phẩm theo id
     * @param id : id cua san pham
     * @return Optional<Product> : tra ve san pham co id tuong ung
     */
    ProductDto findById(Long id);

    /** Lưu sản phẩm
     * - Nếu sản phẩm chưa có id thì thực hiện persist (them moi)
     * - Nếu sản phẩm đã có id thì thực hiện merge (cap nhat)
     * @param productDto : san pham can luu
     * @return Product : tra ve san pham da duoc luu
     */
    ProductDto save(ProductDto productDto);

    /** cap nhat trang thai san pham
     * - Neu sann pham ton tai thi cap nhat trang thai (ACTIVE: 1, INACTIVE: 0, TERMINATED: -1)
     *
     * @param id
     * @param status
     * @return
     */
    ProductDto updateStatus(Long id, ProductStatus status);

    /** Tìm sản phẩm theo nhà sản xuất
     * @param manufacturer : ten nha san xuat
     * @return List<Product> : tra ve danh sach san pham cua nha san xuat
     */
    List<ProductDto> findByManufacturer(String manufacturer);

    /** Tìm sản phẩm theo tên
     * @param name : ten san pham
     * @return List<Product> : tra ve danh sach san pham co ten tuong ung
     */
    List<ProductDto> findByName(String name);

    /** Tìm sản phẩm theo trạng thái
     * @param status : trạng thái sản phẩm (ACTIVE: 1, INACTIVE: 0, TERMINATED: -1)s
     * @return List<Product> : tra ve danh sach san pham co trang thai tuong ung
     */
    List<ProductDto> findByStatus(ProductStatus status);

    /** Them gia cho san pham
     * @param productPriceDto: gia san pham
     * @return Product : tra ve san pham da duoc them gia
     */
    ProductDto savePrice(ProductPriceDto productPriceDto);

    /** tim danh sach san pham theo trang thai va lay ra muc gia cuoi cung ap dung cho san pham do
     * @param status : trang thai san pham
     * @return List<ProductDto> : tra ve danh sach san pham tuong ung voi trang thais
     */
    List<ProductDto> findByStatusAndLastPrice(ProductStatus status);
}
