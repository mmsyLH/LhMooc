package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CommentCourse;
import asia.lhweb.lhmooc.service.CommentCourseService;
import asia.lhweb.lhmooc.service.CourseCategoryService;
import asia.lhweb.lhmooc.service.impl.CommentCourseServiceImpl;
import asia.lhweb.lhmooc.service.impl.CourseCategoryServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

/**
 * 课程评论servlet
 * Author: 罗汉
 * Date: 2024/02/26
 */
@WebServlet()
public class CommentServlet extends LhHttpServlet {


    private CourseCategoryService courseCategoryService = new CourseCategoryServiceImpl(); // 课程分类服务类
    //课程评论服务类
    private CommentCourseService commentCourseService = new CommentCourseServiceImpl();
    private Gson gson = new Gson(); // Google的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public CommentServlet() {

    }

    /**
     * 分页查询评论
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void page(LhRequest req, LhResponse resp) {
        // todo 鉴权！！！  判空！！！ 后台懒得做了
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        String content = req.getParameter("content");

        CommentCourse commentCourse = new CommentCourse();
        commentCourse.setContent(content);
        // 调用课程分类服务类进行模糊查询课程分类
        Page<CommentCourse> page = commentCourseService.page(commentCourse,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String presJson = gson.toJson(Result.success(page, "分页查询评论"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 真实删除课程分类
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void delete(LhRequest req, LhResponse resp) {
        String id = req.getParameter("commentid");

        // 检查categoryid是否为null或空
        if (!DataUtils.handleNullOrEmpty(resp, gson, id)){
            return;
        }
        CommentCourse commentCourse = new CommentCourse();
        commentCourse.setCommentid(Integer.valueOf(id));
        boolean res = commentCourseService.realDelete(commentCourse);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
