package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.bean.Course;
import asia.lhweb.lhmooc.service.CourseService;
import asia.lhweb.lhmooc.service.impl.CourseServiceImpl;
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
    private CourseService courseService;// 用户服务类
    private Gson gson;// 谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public CourseServlet() {
        courseService = new CourseServiceImpl();
    }

    /**
     * 获取排序后的课程
     *
     * @param request  请求
     * @param response 响应
     */
    public void getSortCoursesTop8(LhRequest request, LhResponse response) {
        String sort = request.getParameter("sort");
        List<Course> coursesList = courseService.getSortCoursesTop8(sort);
    }

    @Override
    public void destroy() {

    }
}
