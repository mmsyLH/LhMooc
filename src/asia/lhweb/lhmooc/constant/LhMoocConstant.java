package asia.lhweb.lhmooc.constant;

/**
 * 罗汉慕课项目的常量
 *
 * @author 罗汉
 * @date 2024/03/11
 */
public interface LhMoocConstant {
    /**
     * 盐
     */
    String SALT = "lh";
    /**
     * 项目名称
     */
    String SERVER_NAME = "LhMooc";
    /**
     * 应用名称
     */
    String WEBAPP_NAME = "webapp";
    /**
     * 动作名称
     */
    String ACTION_NAME = "action";
    /**
     * Mooc img目录
     */
    String MOOC_IMG_DIRECTORY="";
    /**
     * 最大请求大小
     */
    int MAX_REQUEST_SIZE = 1024 * 1024*50; // 设置最大请求大小为50MB
}
