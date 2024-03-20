package asia.lhweb.lhmooc.http;

import asia.lhweb.lhmooc.constant.LhMoocConstant;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 罗汉httpservlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public abstract class LhHttpServlet implements LhServlet {
    @Override
    public void service(LhRequest request, LhResponse response) throws IOException {
        // equalsIgnoreCase比较两个字符串是否相等，且忽略大小写
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // this的动态绑定非常非常重要  谁调用的就是谁  真正的运行类型
            doGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            doPost(request, response);
        } else {// 默认执行get方法
            this.doGet(request, response);
        }
    }

    /**
     * 做得到
     *
     * @param request  请求
     * @param response 响应
     */
    public void doGet(LhRequest request, LhResponse response) {
        try {
            // 获取前端传来的动作
            String action = request.getParameter(LhMoocConstant.ACTION_NAME);
            Method declaredMethod = this.getClass().getDeclaredMethod(action, LhRequest.class, LhResponse.class);
            System.err.println("declaredMethod:"+declaredMethod);
            // 执行方法
            declaredMethod.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void doPost(LhRequest request, LhResponse response) {
        this.doGet(request, response);
    }
}
