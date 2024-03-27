package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.Orders;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import asia.lhweb.lhmooc.service.OrdersService;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.impl.OrdersServiceImpl;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

/**
 * 订单servlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class OrdersServlet extends LhHttpServlet {
    private OrdersService ordersService = new OrdersServiceImpl();
    private MoocUserService userService = new MoocUserServiceImpl();
    private Gson gson = new Gson();

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }

    public OrdersServlet() {

    }

    /**
     * 通过用户ID获取订单页面并进行降序分页展示
     *
     * @param request  包含请求参数的对象，其中应包含需要查询的用户ID。
     * @param response 用于返回查询结果的对象。
     */
    public void getOrdersPageByUserId(LhRequest request, LhResponse response) {
        String userId = request.getParameter("userId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");

        if (!DataUtils.handleNullOrEmpty(response, gson, userId, pageNo, pageSize)) {
            return;
        }

        String cookies = request.getHeardParameter("Cookie");

        MoocUser cookieUser = userService.parseCookieToMoocUser(cookies, gson);

        if (!userService.checkUserPermission(userId, cookieUser)) {
            response.writeToJson(gson.toJson(Result.error("权限验证失败 您不是账户本人或者管理员")));
            return;
        }

        Result<Page<Orders>> result = ordersService.getOrdersPageByUserId(Integer.parseInt(userId), Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        response.writeToJson(gson.toJson(result));
    }
}
