package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.Orders;

/**
 * 订单服务
 *
 * @author Administrator
 * @description 针对表【orders(订单表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface OrdersService {

    /**
     * 根据用户id获取订单页面
     *
     * @param userId   用户id
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @return {@link Result}<{@link Page}<{@link Orders}>>
     */
    Result<Page<Orders>> getOrdersPageByUserId(int userId, int pageNo, int pageSize);
}
