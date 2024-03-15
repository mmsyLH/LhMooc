package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseCategory;
import asia.lhweb.lhmooc.service.CourseCategoryService;
import asia.lhweb.lhmooc.service.impl.CourseCategoryServiceImpl;
import com.google.gson.Gson;

import java.util.List;

/**
 * 课程分类servlet
 * Author: 罗汉
 * Date: 2024/02/26
 */
@WebServlet()
public class CategoryServlet extends LhHttpServlet {
    private CourseCategoryService courseCategoryService = new CourseCategoryServiceImpl(); // 课程分类服务类
    private Gson gson = new Gson(); // Google的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public CategoryServlet() {

    }

    /**
     * 分页查询课程分类
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void page(LhRequest req, LhResponse resp) {
        // todo 鉴权！！！  判空！！！
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        // 调用课程分类服务类进行模糊查询课程分类
        Page<CourseCategory> page = courseCategoryService.page(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String presJson = gson.toJson(Result.success(page, "分页查询成功"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 添加课程分类信息。
     *
     * @param req  包含客户端请求数据的对象，其中应包含要更新的分类名称。
     * @param resp 用于向客户端返回响应数据的对象。
     */
    public void add(LhRequest req, LhResponse resp) {
        // 从请求中获取分类名称参数
        String categoryName = req.getParameter("categoryname");

        // 检查分类名称是否为空，若为空则返回错误信息
        if (categoryName == null || categoryName.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("添加课程分类失败，上传的属性不能为空")));
            return;
        }

        // 创建课程分类对象，并设置分类名称
        CourseCategory category = new CourseCategory();
        category.setCategoryname(categoryName.trim());

        // 尝试添加课程分类到数据库，返回添加结果
        boolean res = courseCategoryService.add(category);
        // 根据添加结果生成相应的响应信息
        String jsonResponse = res ? gson.toJson(Result.success("添加课程分类成功")) : gson.toJson(Result.error("添加课程分类失败"));

        // 将响应信息写回客户端
        resp.writeToJson(jsonResponse);
    }

    /**
     * 更新课程分类
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void update(LhRequest req, LhResponse resp) {
        String id = req.getParameter("categoryid");
        String categoryName = req.getParameter("categoryname");

        // 检查必要属性是否为null或空
        if (id == null || id.isEmpty() || categoryName == null || categoryName.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("更新课程分类失败，上传的属性不能为空")));
            return;
        }
        // 封装属性在对象中
        CourseCategory category = new CourseCategory();
        category.setCategoryid(Integer.parseInt(id));
        category.setCategoryname(categoryName.trim());

        boolean res = courseCategoryService.update(category);
        String jsonResponse = res ? gson.toJson(Result.success("更新课程分类成功")) : gson.toJson(Result.error("更新课程分类失败"));

        resp.writeToJson(jsonResponse);
    }


    /**
     * 获取所有的课程分类信息。
     *
     * @param req 请求对象，用于接收客户端请求数据。
     * @param resp 响应对象，用于向客户端发送响应数据。
     */
    public void getAll(LhRequest req, LhResponse resp) {
        // 从课程分类服务获取全部分类列表
        List<CourseCategory> courseCategoryList = courseCategoryService.getAll();

        // 根据获取的结果，构造相应的JSON响应
        String jsonResponse = !courseCategoryList.isEmpty() ? gson.toJson(Result.success(courseCategoryList,"获取全部分类成功")) : gson.toJson(Result.error("获取全部失败"));

        // 将构造的JSON响应写回给客户端
        resp.writeToJson(jsonResponse);
    }

    /**
     * 逻辑删除删除课程分类
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void delete(LhRequest req, LhResponse resp) {
        String id = req.getParameter("categoryid");

        // 检查categoryid是否为null或空
        if (id == null || id.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("需要删除的id缺失")));
            return;
        }
        CourseCategory category = new CourseCategory();
        category.setCategoryid(Integer.valueOf(id));
        boolean res = courseCategoryService.delete(category);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    /**
     * 真正删除课程分类
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void realDelete(LhRequest req, LhResponse resp) {
        String id = req.getParameter("categoryid");

        // 检查categoryid是否为null或空
        if (id == null || id.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("需要删除的id缺失")));
            return;
        }
        CourseCategory category = new CourseCategory();
        category.setCategoryid(Integer.valueOf(id));
        boolean res = courseCategoryService.realDelete(category);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
