package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.OrdersDAO;
import asia.lhweb.lhmooc.dao.impl.OrdersDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.Orders;
import asia.lhweb.lhmooc.service.OrdersService;

import java.util.List;

/**
 * 订单服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class OrdersServiceImpl implements OrdersService {
    // 订单dao
    private OrdersDAO ordersDAO = new OrdersDAOImpl();

    /**
     * 根据用户id获取订单页面
     *
     * @param userId   用户id
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @return {@link Result}<{@link Page}<{@link Orders}>>
     */
    @Override
    public Result<Page<Orders>> getOrdersPageByUserId(int userId, int pageNo, int pageSize) {
        Orders orders = new Orders();
        orders.setUserid(userId);

        List<Orders> ordersList = ordersDAO.selectAll(orders);
        if (ordersList.size() == 0) {
            return Result.error("没有订单");
        }
        // 对查询结果按照收藏时间进行降序排序
        ordersList.sort((o1, o2) -> o2.getCreatetime().compareTo(o1.getCreatetime()));

        Page<Orders> page = new Page<>();
        page=page.getPageByList(pageNo, pageSize, ordersList);

        return Result.success(page,"查询个人订单成功");
    }
}
