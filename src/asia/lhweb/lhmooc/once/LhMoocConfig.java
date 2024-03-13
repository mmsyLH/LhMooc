package asia.lhweb.lhmooc.once;

import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.factory.ServletFactory;

/**
 * 罗汉慕课项目配置启动类
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class LhMoocConfig {
    public static void run() {
        // 初始化servlet工厂配置
        ServletFactory.getInstance();

        // 初始化bean工程
        BeanFactory.getInstance();
    }
}
