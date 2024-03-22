package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseChapter;
import asia.lhweb.lhmooc.service.CourseChapterService;
import asia.lhweb.lhmooc.service.impl.CourseChapterServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

/**
 * 课程章节 Servlet
 * Author: 罗汉
 * Date: 2024/02/26
 */
@WebServlet()
public class ChapterServlet extends LhHttpServlet {

    private CourseChapterService courseChapterService = new CourseChapterServiceImpl();
    private Gson gson = new Gson();

    @Override
    public void init() throws Exception {

    }

    public ChapterServlet() {

    }

    /**
     * 分页查询课程章节记录
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void page(LhRequest req, LhResponse resp) {
        // todo 鉴权！！！  判空！！！ 后台懒得做了
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        String courseId = req.getParameter("courseId");

        CourseChapter courseChapter = new CourseChapter();
        if (!"".equals(courseId)) {
            courseChapter.setCouseid(Integer.parseInt(courseId));
        }
        // 调用点赞服务类进行查询点赞记录
        Page<CourseChapter> page = courseChapterService.pageByAnd(courseChapter, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String jsonResponse = gson.toJson(Result.success(page, "分页查询课程章节记录"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(jsonResponse);
    }

    /**
     * 根据类别ID获取该类别的课程列表。 后台界面的分页
     *
     * @param req  请求对象，包含客户端请求的数据。
     * @param resp 响应对象，用于向客户端返回处理结果。
     */
    // public void getByCourse(LhRequest req, LhResponse resp) {
    //     String id = req.getParameter("categoryId");
    //
    //     // 检查请求中是否提供了必要的categoryId参数
    //     if (id == null || id.isEmpty()) {
    //         id = "1";
    //     }
    //     Course course = new Course();
    //     course.setCategoryid(Integer.parseInt(id));
    //     // 从课程分类服务获取全部分类列表
    //     List<Course> courseList = courseChapterService.getByCourse(course);
    //
    //     // 根据获取的结果，构造相应的JSON响应
    //     String jsonResponse = !courseList.isEmpty() ? gson.toJson(Result.success(courseList, "获取该类课程成功")) : gson.toJson(Result.error("该类里没有课程！！！"));
    //
    //     // 将构造的JSON响应写回给客户端
    //     resp.writeToJson(jsonResponse);
    // }

    /**
     * 添加章节
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void add(LhRequest req, LhResponse resp) {
        String categoryid = req.getParameter("categoryid");
        String courseid = req.getParameter("courseid");
        String chaptername = req.getParameter("chaptername");

        // 检查必要属性是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, categoryid, courseid, chaptername)) {
            return;
        }
        // todo 注意 全部的后台管理应该都做判空（是否存在）、鉴权 这里就懒得做了
        Result result = courseChapterService.add(Integer.parseInt(courseid), chaptername);
        resp.writeToJson(gson.toJson(result));
    }

    /**
     * 更新章节
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void update(LhRequest req, LhResponse resp) {
        String categoryid = req.getParameter("categoryid");
        String courseid = req.getParameter("courseid");
        String chapterid = req.getParameter("chapterid");
        String chaptername = req.getParameter("chaptername");
        String isdelete = req.getParameter("isdelete");


        // 检查必要属性是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, categoryid, courseid, chapterid, chaptername, isdelete)) {
            return;
        }
        // todo 注意 全部的后台管理应该都做判空（是否存在）、鉴权 这里就懒得做了


        // 调用userService，根据ID获取用户信息
        Result result = courseChapterService.update(Integer.parseInt(chapterid), chaptername);

        // 将查询结果转换为JSON，并通过response返回
        resp.writeToJson(gson.toJson(result));
    }

    /**
     * 真实删除
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void realDelete(LhRequest req, LhResponse resp) {
        String chapterid = req.getParameter("chapterid");

        // 检查categoryId是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, chapterid)) {
            return;
        }
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setChapterid(Integer.valueOf(chapterid));
        boolean success = courseChapterService.realDelete(courseChapter);
        String jsonResponse = success ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
