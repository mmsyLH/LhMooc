package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;


/**
 * 支付servlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class PayServlet extends LhHttpServlet {
    private MoocUserService userService= new MoocUserServiceImpl();// 用户服务类
    private Gson gson = new Gson();// 谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public PayServlet() {

    }


    /**
     * 用户余额充值
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void recharge(LhRequest req, LhResponse resp) {
        String userId = req.getParameter("userId");
        String wallet = req.getParameter("wallet");

        //参数为空
        if (!DataUtils.handleNullOrEmpty(resp, gson, userId, wallet)){
            return;
        }

        // 在这里添加验证充值金额是否为数字的逻辑
        if (!DataUtils.isNumeric(wallet)) {
            resp.writeToJson(gson.toJson(Result.error("充值金额必须是数字")));
            return;
        }

        // 从携带的请求中获取用户信息
        String cookies = req.getHeardParameter("Cookie");

        // 验证权限
        // 1得到cookie中的用户信息
        MoocUser cookieUser = userService.parseCookieToMoocUser(cookies, gson);
        // 2调用方法
        if (!userService.checkUserPermission(userId, cookieUser)) {
            resp.writeToJson(gson.toJson(Result.error("权限验证失败 您不是账户本人或者管理员")));
            return;
        }
        // 有权限后再进行业务逻辑

        // 调用userService，根据ID获取用户信息
        Result result = userService.recharge(Integer.parseInt(userId),Double.parseDouble(wallet));

        // 将查询结果转换为JSON，并通过response返回
        resp.writeToJson(gson.toJson(result));
    }

    @Override
    public void destroy() {

    }
}
