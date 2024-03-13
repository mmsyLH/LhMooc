package asia.lhweb.lhmooc.servlet.admin;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.service.MoocAdminService;
import asia.lhweb.lhmooc.service.impl.MoocAdminServiceImpl;
import com.google.gson.Gson;


/**
 * 用户servlet
 * 这里处理和用户相关的信息
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet("admin/adminServlet")
public class AdminServlet extends LhHttpServlet {
    private MoocAdminService adminService=new MoocAdminServiceImpl();//用户服务类
    private Gson gson;//谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }
    public AdminServlet() {

    }

    @Override
    public void destroy() {

    }
}
