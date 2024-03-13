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
     * 更新课程分类
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void update(LhRequest req, LhResponse resp) {
        gson = new Gson();
        String id = req.getParameter("categoryid");
        String categoryName = req.getParameter("categoryname");

        // 检查必要属性是否为null或空
        if (id == null || id.isEmpty() || categoryName == null || categoryName.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("更新失败，上传的属性不能为空")));
            return;
        }
        // 封装属性在对象中
        CourseCategory category = new CourseCategory();
        category.setCategoryid(Integer.parseInt(id));
        category.setCategoryname(categoryName.trim());

        boolean res = courseCategoryService.update(category);
        String jsonResponse = res ? gson.toJson(Result.success("更新成功")) : gson.toJson(Result.error("更新失败"));

        resp.writeToJson(jsonResponse);
    }

    /**
     * 删除课程分类
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

    @Override
    public void destroy() {

    }
}
