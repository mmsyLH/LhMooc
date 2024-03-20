package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.FollowCourse;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import asia.lhweb.lhmooc.service.FollowCourseService;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.impl.FollowCourseServiceImpl;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;


/**
 * 收藏servlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class FollowServlet extends LhHttpServlet {
    private FollowCourseService followCourseService = new FollowCourseServiceImpl();
    private MoocUserService userService = new MoocUserServiceImpl();
    private Gson gson = new Gson();// 谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public FollowServlet() {

    }

    /**
     * 通过id获取关注页面 并且降序分页展示
     *
     * @param request  包含请求参数的对象，其中应包含需要查询的用户ID。
     * @param response 用于返回查询结果的对象。
     */
    public void getFollowPageById(LhRequest request, LhResponse response) {
        // 从请求中获取用户ID
        String userId = request.getParameter("userId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");

        // 检查ID是否为空，若为空或错误则通过response进行相应处理，并返回
        if (!DataUtils.handleNullOrEmpty(response, gson, userId, pageNo, pageSize)) {
            return;
        }

        // 从携带的请求中获取用户信息
        String cookies = request.getHeardParameter("Cookie");

        // 验证权限
        // 1得到cookie中的用户信息
        MoocUser cookieUser = userService.parseCookieToMoocUser(cookies, gson);
        // 2调用方法
        if (!userService.checkUserPermission(userId, cookieUser)) {
            response.writeToJson(gson.toJson(Result.error("权限验证失败 您不是账户本人或者管理员")));
            return;
        }
        // 有权限后再查询

        // 调用userService，根据ID获取用户信息
        Result<Page<FollowCourse>> result = followCourseService.getFollowPageById(Integer.parseInt(userId), Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        // 将查询结果转换为JSON，并通过response返回
        response.writeToJson(gson.toJson(result));
    }


    /**
     * 用户更新
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void userUpdate(LhRequest req, LhResponse resp) {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");// 可以为空
        String avatar = req.getParameter("avatar");
        String wallet = req.getParameter("wallet");

        if (!DataUtils.handleNullOrEmpty(resp, gson, id, nickname, avatar, wallet)) {
            return;
        }


        // 封装属性在对象中
        MoocUser moocUser = new MoocUser();
        moocUser.setId(Integer.parseInt(id));
        if (password != null && !password.isEmpty()) {
            moocUser.setPassword(password);
        }

        moocUser.setNickname(nickname);
        moocUser.setEmail(email);
        moocUser.setAvatar(avatar);
        moocUser.setWallet(Double.parseDouble(wallet));


        // 调用userService，根据ID获取用户信息
        Result result = userService.userUpdate(moocUser);

        // 将查询结果转换为JSON，并通过response返回
        resp.writeToJson(gson.toJson(result));
    }

    @Override
    public void destroy() {

    }
}
