package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.service.PlayHistoryService;
import asia.lhweb.lhmooc.service.impl.MoocUserServiceImpl;
import asia.lhweb.lhmooc.service.impl.PlayHistoryServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;


/**
 * 播放历史servlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class PlayHistoryServlet extends LhHttpServlet {
    private MoocUserService userService = new MoocUserServiceImpl();// 用户服务类

    private PlayHistoryService playHistoryService = new PlayHistoryServiceImpl();// 历史记录服务
    private Gson gson = new Gson();// 谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public PlayHistoryServlet() {

    }


    /**
     * 用户余额充值
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    public void getByUserIdAndVideoName(LhRequest req, LhResponse resp) {
        String userId = req.getParameter("userId");
        String videoName = req.getParameter("videoName");

        // 请求参数为空
        if (!DataUtils.handleNullOrEmpty(resp, gson, userId)) {
            return;
        }
        //
        if (DataUtils.handleNullOrEmpty(resp, gson, videoName)){
            videoName="";
        }

        // 没有权限
        if (userService.isNoMeOrAdmin(req, resp, userId, gson)) return;

        // 调用userService，根据ID获取用户信息
        Result result = playHistoryService.getByUserIdAndVideoName(Integer.parseInt(userId), videoName);

        // 将查询结果转换为JSON，并通过response返回
        resp.writeToJson(gson.toJson(result));
    }

    @Override
    public void destroy() {

    }
}
