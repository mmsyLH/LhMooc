package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
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
    private MoocUserService userService;// 用户服务类
    private Gson gson = new Gson();// 谷歌的解析json的工具类

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
        // 获取用户和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // System.err.println("password"+ password );
        // 新建用户
        MoocUser moocUser = new MoocUser();
        moocUser.setUsername(username);
        moocUser.setPassword(password);
        // 调用service的登录方法
        MoocUser loginUser = userService.login(moocUser);
        String presJson;
        if (loginUser != null) {
            presJson = gson.toJson(Result.success(loginUser,  "登录成功"));
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

    }

    /**
     * 分页
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void page(LhRequest req, LhResponse resp) {
        // todo 鉴权！！！  判空！！！
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        // 调用trainService去模糊查询车站
        Page<MoocUser> page = userService.page(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        gson = new Gson();
        String presJson = gson.toJson(Result.success(page, "分页查询成功"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 更新用户
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void update(LhRequest req, LhResponse resp) {
        gson = new Gson();
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");
        String avatar = req.getParameter("avatar");
        String wallet = req.getParameter("wallet");
        String userrole = req.getParameter("userrole");

        // 检查必要属性是否为null或空
        if (id == null || id.isEmpty() ||
                username == null || username.isEmpty() ||
                nickname == null || nickname.isEmpty() ||
                email == null || email.isEmpty() ||
                avatar == null || avatar.isEmpty() ||
                wallet == null || wallet.isEmpty() ||
                userrole == null || userrole.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("更新失败，上传的属性不能为空")));
            return;
        }
        // 封装属性在对象中
        MoocUser moocUser = new MoocUser();
        moocUser.setId(Integer.parseInt(id));
        moocUser.setUsername(username);
        if (password != null && !password.isEmpty()) {
            moocUser.setPassword(password);
        }
        moocUser.setNickname(nickname);
        moocUser.setEmail(email);
        moocUser.setAvatar(avatar);
        moocUser.setWallet(Double.parseDouble(wallet));
        System.out.println("userrole=" + userrole.trim());
        if ("0".equals(userrole.trim())) {
            moocUser.setUserrole(0);
        } else {
            moocUser.setUserrole(Integer.parseInt(userrole));
        }


        boolean res = userService.update(moocUser);
        String jsonResponse = res ? gson.toJson(Result.success("更新成功")) : gson.toJson(Result.error("更新失败"));

        resp.writeToJson(jsonResponse);
    }

    /**
     * 删除
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void delete(LhRequest req, LhResponse resp) {
        String id = req.getParameter("id");

        // 检查stationid是否为null或空
        if (id == null || id.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("需要删除的id缺失")));
            return;
        }
        MoocUser moocUser = new MoocUser();
        moocUser.setId(Integer.valueOf(id));
        boolean res = userService.delete(moocUser);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }


    @Override
    public void destroy() {

    }
}
