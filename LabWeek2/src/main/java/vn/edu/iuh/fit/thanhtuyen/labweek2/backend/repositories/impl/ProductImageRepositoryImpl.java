package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.ProductImage;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.ProductImageRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductImageRepositoryImpl implements ProductImageRepository {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<ProductImage> findByProductId(Long productId) {
        return em.createNamedQuery("ProductImage.findByProductId", ProductImage.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public Optional<ProductImage> findById(Long id) {
        return Optional.of(em.find(ProductImage.class, id));
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        if(productImage.getId() == null) {
            em.persist(productImage);
        } else {
            Optional<ProductImage> productImageUpdate = findById(productImage.getId());
            if (productImageUpdate.isPresent()) {
                em.merge(productImage);
            } else {
                productImage.setId(null);
                em.persist(productImage);
            }
        }
        return productImage;
    }
}
