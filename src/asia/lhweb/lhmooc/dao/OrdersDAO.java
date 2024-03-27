package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.Orders;

import java.util.List;

/**
 * 订单服务
 *
 * @author Administrator
 * @description 针对表【orders(订单表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface OrdersDAO {

    /**
     * 保存
     *
     * @param orders 订单
     * @return boolean
     */
    int save(Orders orders);

    List<Orders> selectAll(Orders orders);
}
