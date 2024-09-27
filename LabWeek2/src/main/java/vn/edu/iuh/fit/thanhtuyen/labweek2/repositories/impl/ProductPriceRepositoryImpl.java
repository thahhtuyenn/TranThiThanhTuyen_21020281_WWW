package vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPrice;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.utils.AppUtils;

import java.util.List;
import java.util.Optional;

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
