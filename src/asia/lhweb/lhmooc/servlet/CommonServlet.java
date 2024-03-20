package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;


/**
 * 收藏servlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class CommonServlet extends LhHttpServlet {
    private Gson gson = new Gson();// 谷歌的解析json的工具类

    @Override
    public void init() throws Exception {

    }

    public CommonServlet() {

    }

    /**
     * 获取md5 STR
     *
     * @param request  请求
     * @param response 响应
     */
    public void getMd5Str(LhRequest request, LhResponse response) {
        String text = request.getParameter("text");
        if (!DataUtils.handleNullOrEmpty(response, gson, text)) {
            return;
        }
        String md5Str = DataUtils.encryptPassword(text);

        response.writeToJson(gson.toJson(Result.success(md5Str, "获取加密后的文本成功")));
    }

    @Override
    public void destroy() {

    }
}
