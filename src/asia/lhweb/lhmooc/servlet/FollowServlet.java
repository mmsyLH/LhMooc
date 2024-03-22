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
     * 分页查询收藏记录
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void page(LhRequest req, LhResponse resp) {
        // todo 鉴权！！！  判空！！！ 后台懒得做了
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        String userID = req.getParameter("userID");

        FollowCourse followCourse = new FollowCourse();
        if (!"".equals(userID)){
            followCourse.setUserid(Integer.parseInt(userID));
        }
        // 调用收藏服务类进行查询收藏记录
        Page<FollowCourse> page = followCourseService.page(followCourse, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String presJson = gson.toJson(Result.success(page, "分页查询收藏记录"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 删除收藏记录
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void delete(LhRequest req, LhResponse resp) {
        String id = req.getParameter("followid");

        // 检查followid是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, id)){
            return;
        }
        FollowCourse followCourse = new FollowCourse();
        followCourse.setFollowid(Integer.valueOf(id));
        boolean res = followCourseService.realDelete(followCourse);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
