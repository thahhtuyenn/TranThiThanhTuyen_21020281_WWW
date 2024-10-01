package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.OrderDetail;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.OrderDetailId;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories.OrderDetailRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<OrderDetail> saveAll(List<OrderDetail> orderDetails) {
        List<OrderDetail> orderDetailsNew = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {

            //Lay ra id cua order
            OrderDetailId orderDetailId = orderDetail.getId();
            //Tim order detail theo id cua order va id cua product
            List<OrderDetail> orderDetailList = em.createNamedQuery("OrderDetail.findByOrderIdAndProductId", OrderDetail.class)
                    .setParameter("orderId", orderDetailId.getOrderId())
                    .setParameter("productId", orderDetailId.getProductId())
                    .getResultList();
            OrderDetail odByid = !orderDetailList.isEmpty() ? orderDetailList.get(0) : null;
            //Neu order detail tim theo id cua order va id cua product khong ton tai thi them moi
            if (odByid == null) {
                em.persist(orderDetail);
            }else {
                orderDetail = em.merge(orderDetail);
            }
            orderDetailsNew.add(orderDetail);

        }
        return orderDetails;
    }
}
