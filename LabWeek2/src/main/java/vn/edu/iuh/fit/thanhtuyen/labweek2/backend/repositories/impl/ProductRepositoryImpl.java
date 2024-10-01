package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Product;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.ProductImage;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.ProductStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.of(em.find(Product.class, id));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setStatus(ProductStatus.ACTIVE);
            em.persist(product);
        } else {
            em.merge(product);
        }
        return product;
    }

    @Override
    public Product updateStatus(Long id, ProductStatus status) {
        Optional<Product> product = findById(id);
        if(product.isPresent()){
            Product p = product.get();
            p.setStatus(status);
            return em.merge(p);
        }
        return null;
    }

    @Override
    public List<Product> findByManufacturer(String manufacturer) {
        return em.createNamedQuery("Product.findByManufacturer", Product.class)
                .setParameter("manufacturer", manufacturer)
                .getResultList();
    }

    @Override
    public List<Product> findByName(String name) {
        return em.createNamedQuery("Product.findByName", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public ProductImage saveImage(ProductImage productImage) {
        if (productImage.getId() == null)
            em.persist(productImage);
        else
            em.merge(productImage);
        return productImage;
    }

    @Override
    public Optional<ProductImage> findImageBImageId(Long imageId) {
        return Optional.of(em.find(ProductImage.class, imageId));
    }

    @Override
    public List<Product> findByStatus(ProductStatus status) {
        return em.createNamedQuery("Product.findByStatus", Product.class)
                .setParameter("status", status)
                .getResultList();
    }
}
