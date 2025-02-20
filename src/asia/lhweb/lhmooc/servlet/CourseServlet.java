package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.Course;
import asia.lhweb.lhmooc.model.vo.CourseVo;
import asia.lhweb.lhmooc.service.CourseService;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.impl.CourseServiceImpl;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * 用户servlet
 * 这里处理和用户相关的信息
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class CourseServlet extends LhHttpServlet {
    private CourseService courseService = new CourseServiceImpl();

    private MoocUserService userService = new MoocUserServiceImpl();
    // 用户服务类
    private Gson gson = new Gson();// 谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public CourseServlet() {

    }

    /**
     * 添加
     *
     * @param request  请求
     * @param response 响应
     */
    public void buyCourse(LhRequest request, LhResponse response) {
        String userId = request.getParameter("userId");
        String courseId = request.getParameter("courseId");

        // 判空
        if (DataUtils.isAnyNullOrEmpty(userId, courseId)) {
            return;
        }

        // 鉴权
        if (userService.isNoMeOrAdmin(request, response, userId, gson)) return;

        Result result = courseService.buyCourse(Integer.parseInt(userId), Integer.parseInt(courseId));

        response.writeToJson(gson.toJson(result));

    }

    public void add(LhRequest req, LhResponse resp) {
        // 从请求中获取分类名称参数
        String courseName = req.getParameter("coursename");
        String categoryid = req.getParameter("categoryid");
        String profile = req.getParameter("profile");
        String price = req.getParameter("price");

        // 检查分类名称是否为空，若为空则返回错误信息
        if (!DataUtils.handleNullOrEmpty(resp, gson, courseName, categoryid, profile, price)) {
            return;
        }

        // 创建课程分类对象，并设置分类名称
        Course course = new Course();
        course.setCoursename(courseName.trim());
        course.setCategoryid(Integer.parseInt(categoryid.trim()));
        course.setProfile(profile.trim());
        course.setPrice(Double.parseDouble(price.trim()));
        // 尝试添加课程分类到数据库，返回添加结果
        boolean res = courseService.add(course);
        // 根据添加结果生成相应的响应信息
        String jsonResponse = res ? gson.toJson(Result.success("添加成功")) : gson.toJson(Result.error("添加失败"));

        // 将响应信息写回客户端
        resp.writeToJson(jsonResponse);
    }

    /**
     * 根据课程id获取课程详情
     *
     * @param request  请求对象
     * @param response 响应对象
     */
    public void getCourseDetailById(LhRequest request, LhResponse response) {
        // 获取课程ID
        String courseId = request.getParameter("courseId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        // 检查课程ID是否为空
        if (!DataUtils.handleNullOrEmpty(response, gson, courseId,pageNo,pageSize)) {
            // 如果课程ID为空，则直接返回，不执行下面的逻辑
            return;
        }

        // 根据课程ID获取课程详情
        Result<CourseVo> jsonResponse = courseService.getCourseDetail(Integer.parseInt(courseId),Integer.parseInt(pageNo),Integer.parseInt(pageSize));

        // 返回json对象
        response.writeToJson(gson.toJson(jsonResponse));
    }

    /**
     * 更新章节
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void update(LhRequest req, LhResponse resp) {
        String courseid = req.getParameter("courseid");
        String coursename = req.getParameter("coursename");
        String categoryid = req.getParameter("categoryid");
        String profile = req.getParameter("profile");
        String price = req.getParameter("price");
        // String isdelete = req.getParameter("isdelete");


        // 检查必要属性是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, categoryid, courseid, coursename, profile, price)) {
            return;
        }
        // todo 注意 全部的后台管理应该都做判空（是否存在）、鉴权 这里就懒得做了


        // 调用userService，根据ID获取用户信息
        Result result = courseService.update(Integer.parseInt(courseid), coursename, profile, price);

        // 将查询结果转换为JSON，并通过response返回
        resp.writeToJson(gson.toJson(result));
    }

    /**
     * 获取排序后的课程
     *
     * @param request  请求
     * @param response 响应
     */
    public void getSortCoursesTop8(LhRequest request, LhResponse response) {
        String sortType = request.getParameter("sortType");//"0"表示默认不排序，"1"表示按收藏量排序，"2"表示按点赞量排序，"3"表示按评论量排序
        // System.out.println("\"0\"表示默认不排序，\"1\"表示按收藏量排序，\"2\"表示按点赞量排序，\"3\"表示按评论量排序 sortType:" + sortType);
        List<CourseVo> sortCoursesTop8 = courseService.getSortCoursesTop8(sortType);
        String jsonResponse = !sortCoursesTop8.isEmpty() ? gson.toJson(Result.success(sortCoursesTop8, "查询成功")) : gson.toJson(Result.error("查询失败"));
        // 将响应信息写回客户端
        response.writeToJson(jsonResponse);
    }

    /**
     * 根据类别ID获取该类别的课程列表。 后台界面的分页
     *
     * @param req  请求对象，包含客户端请求的数据。
     * @param resp 响应对象，用于向客户端返回处理结果。
     */
    public void getByCategory(LhRequest req, LhResponse resp) {
        String id = req.getParameter("categoryId");

        // 检查请求中是否提供了必要的categoryId参数
        if (id == null || id.isEmpty()) {
            id = "1";
        }
        Course course = new Course();
        course.setCategoryid(Integer.parseInt(id));
        // 从课程分类服务获取全部分类列表
        List<Course> courseList = courseService.getByCategory(course);

        // 根据获取的结果，构造相应的JSON响应
        String jsonResponse = !courseList.isEmpty() ? gson.toJson(Result.success(courseList, "获取该类课程成功")) : gson.toJson(Result.error("该类里没有课程！！！"));

        // 将构造的JSON响应写回给客户端
        resp.writeToJson(jsonResponse);
    }


    /**
     * 真实删除
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void realDelete(LhRequest req, LhResponse resp) {
        String courseid = req.getParameter("courseid");

        // 检查categoryId是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, courseid)) {
            return;
        }
        Course course = new Course();
        course.setCourseid(Integer.valueOf(courseid));
        boolean success = courseService.realDelete(course);
        String jsonResponse = success ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    /**
     * 根据类别ID获取该类别的课程列表。 用户界面的分页
     *
     * @param req  请求对象，包含客户端请求的数据。
     * @param resp 响应对象，用于向客户端返回处理结果。
     */
    public void pageAndByCategory(LhRequest req, LhResponse resp) {
        String id = req.getParameter("categoryId");
        String sortType = req.getParameter("sortType");//// 1收藏 2点赞 3评论 4时间
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");

        // 检查请求中是否提供了必要的categoryId参数
        if (id == null || id.isEmpty()) {
            id = "1";
        }
        if (sortType == null || sortType.isEmpty()) {
            sortType = "";
        }
        if (pageNo == null || pageNo.isEmpty()) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.isEmpty()) {
            pageSize = "5";
        }
        // 从课程分类服务获取全部分类列表
        Page<CourseVo> page = courseService.pageAndByCategory(Integer.parseInt(id), Integer.parseInt(pageNo), Integer.parseInt(pageSize), sortType);

        // 根据获取的结果，构造相应的JSON响应
        String jsonResponse = !page.isEmpty() ? gson.toJson(Result.success(page, "获取该类课程成功")) : gson.toJson(Result.error("该类里没有课程！！！"));

        // 将构造的JSON响应写回给客户端
        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
