package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.LikeCourse;
import asia.lhweb.lhmooc.service.LikeCourseService;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.impl.LikeCourseServiceImpl;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

/**
 * 课程点赞 Servlet
 * Author: 罗汉
 * Date: 2024/02/26
 */
@WebServlet()
public class LikeServlet extends LhHttpServlet {

    // 课程点赞服务类
    private LikeCourseService likeCourseService = new LikeCourseServiceImpl();
    // 用户服务类
    private MoocUserService userService = new MoocUserServiceImpl();
    private Gson gson = new Gson(); // Google的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public LikeServlet() {

    }

    /**
     * 添加
     *
     * @param request  请求
     * @param response 响应
     */
    public void likeAdd(LhRequest request, LhResponse response) {
        String userId = request.getParameter("userId");
        String courseId = request.getParameter("courseId");

        // 判空
        if (DataUtils.isAnyNullOrEmpty(userId, courseId)) {
            return;
        }

        // 鉴权
        if (userService.isNoMeOrAdmin(request, response, userId, gson)) return;

        Result result = likeCourseService.likeAdd(Integer.parseInt(userId), Integer.parseInt(courseId));

        response.writeToJson(gson.toJson(result));

    }

    /**
     * 分页查询点赞记录
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void page(LhRequest req, LhResponse resp) {
        // todo 鉴权！！！  判空！！！ 后台懒得做了
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        String userID = req.getParameter("userID");


        LikeCourse likeCourse = new LikeCourse();
        if (!"".equals(userID)) {
            likeCourse.setUserid(Integer.parseInt(userID));
        }
        // 调用点赞服务类进行查询点赞记录
        Page<LikeCourse> page = likeCourseService.page(likeCourse, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String presJson = gson.toJson(Result.success(page, "分页查询点赞记录"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 删除点赞记录
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void delete(LhRequest req, LhResponse resp) {
        String id = req.getParameter("likeid");

        // 检查likeid是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, id)) {
            return;
        }
        LikeCourse likeCourse = new LikeCourse();
        likeCourse.setLikeid(Integer.valueOf(id));
        boolean res = likeCourseService.realDelete(likeCourse);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
