package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import com.google.gson.Gson;


/**
 * 用户servlet
 * 这里处理和用户相关的信息
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class UserServlet extends LhHttpServlet {
    private MoocUserService userService;//用户服务类
    private Gson gson;//谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }
    public UserServlet() {
        userService = new MoocUserServiceImpl();
    }

    /**
     * 注册用户
     *
     * @param req  请求对象，包含注册用户的用户名和密码
     * @param resp 响应对象，用于返回注册结果
     */
    public void register(LhRequest req, LhResponse resp) {
        System.out.println("执行了注册方法");
        // 获取请求参数中的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");

        MoocUser moocUser = new MoocUser();
        moocUser.setUsername(username);
        moocUser.setPassword(password);

        // 调用用户服务注册新用户
        boolean registerRes = userService.register(moocUser);

        gson = new Gson();
        // 将注册结果转换为 JSON 字符串
        String presJson;
        if (registerRes) {
            // 注册成功，返回成功信息
            presJson = gson.toJson(Result.success("注册成功"));
        } else {
            // 注册失败，返回错误信息
            presJson = gson.toJson(Result.error("注册失败"));
        }
        // 将 JSON 字符串写入响应对象中
        resp.writeToJson(presJson);
    }


    /**
     * 登录
     * 用户登录的方法登录
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void login(LhRequest req, LhResponse resp) {
        System.out.println("执行了登录方法");
        //获取用户和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //新建用户
        MoocUser moocUser = new MoocUser();
        moocUser.setUsername(username);
        moocUser.setPassword(password);
        //调用service的登录方法
        MoocUser loginUser = userService.login(moocUser);
        gson = new Gson();
        String presJson;
        if (loginUser != null) {
            presJson = gson.toJson(Result.success(loginUser, "登录成功"));
        } else {
            presJson = gson.toJson(Result.error("登录失败"));
        }
        resp.writeToJson(presJson);
    }

    /**
     * 退出登录
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void logout(LhRequest req, LhResponse resp) {
        System.out.println("执行了登录方法");
        //获取用户和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //新建用户
        MoocUser moocUser = new MoocUser();
        moocUser.setUsername(username);
        moocUser.setPassword(password);
        //调用service的登录方法
        MoocUser loginUser = userService.login(moocUser);
        gson = new Gson();
        String presJson;
        if (loginUser != null) {
            presJson = gson.toJson(Result.success(loginUser, "登录成功"));
        } else {
            presJson = gson.toJson(Result.error("登录失败"));
        }
        resp.writeToJson(presJson);
    }
    @Override
    public void destroy() {

    }
}
