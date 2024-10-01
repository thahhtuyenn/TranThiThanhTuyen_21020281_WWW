package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.ProductPrice;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.util.List;

@Transactional
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<ProductPrice> findByProductId(Long productId) {
        return em.createNamedQuery("ProductPrice.findByProductId", ProductPrice.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public ProductPrice findLastPriceByProductId(Long productId) {
        List<ProductPrice> productPrices = em.createNamedQuery("ProductPrice.findLastPriceByProductId", ProductPrice.class)
                .setParameter("productId", productId).getResultList();
        return productPrices.isEmpty() ? null : productPrices.get(0);
    }

    @Override
    public ProductPrice save(ProductPrice productPrice) {
        if (productPrice.getId() == null) {
            em.persist(productPrice);
        } else {
            productPrice = em.merge(productPrice);
        }
        return productPrice;
    }
}
