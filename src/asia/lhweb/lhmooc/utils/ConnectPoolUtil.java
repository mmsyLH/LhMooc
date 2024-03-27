package asia.lhweb.lhmooc.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池工具类，用于管理和复用数据库连接。
 */
public class ConnectPoolUtil {
    // 连接池最大容量
    public static final int maxSize = 20;
    // 初始化连接池，存放空闲的连接
    private static final LinkedList<Connection> initPool = new LinkedList<>();
    // 使用中的连接池，存放正在使用的连接
    private static final LinkedList<Connection> usePool = new LinkedList<>();
    // 连接计数器，用于追踪当前连接池中连接的数量
    private static final AtomicInteger count = new AtomicInteger(0);

    // 私有构造函数，防止实例化
    private ConnectPoolUtil() {

    }

    /**
     * 静态代码块，用于初始化连接池。
     * 在类加载时，尝试建立5个数据库连接，并将这些连接添加到初始化的连接池中。
     * 如果建立连接时发生异常，将打印异常堆栈跟踪信息。
     */
    static {
        // 尝试建立5个数据库连接，并添加到连接池中
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = JDBCUtils2.getConnection(); // 尝试获取一个数据库连接
                count.incrementAndGet(); // 累计已建立的连接数
                // System.out.println("初始化连接池:" + connection); // 打印初始化连接池的信息
                initPool.offer(connection); // 将连接添加到初始化连接池中
            } catch (Exception e) {
                e.printStackTrace(); // 处理异常，打印堆栈跟踪信息
            }
        }
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            if (!initPool.isEmpty()) {
                connection = initPool.poll();
                if (!isValidConnection(connection)) {
                    System.out.println("没有可用连接");
                    return null;
                }
                usePool.offer(connection);
            } else {
                if (count.get() < maxSize) {
                    connection = JDBCUtils2.getConnection();
                    usePool.offer(connection);
                    count.incrementAndGet();
                    System.out.println("获取新连接：" + connection + " 当前连接池大小：" + count.get() + " 当前initPool大小：" + initPool.size() + " 当前usePool大小：" + usePool.size());
                } else {
                    System.out.println("请等待连接：当前连接池大小：" + count.get() + " 当前initPool大小：" + initPool.size() + " 当前usePool大小：" + usePool.size());
                    this.wait(2000);
                    connection = getConnection();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public synchronized boolean isInUse(Connection connection) {
        return usePool.contains(connection);
    }

    /**
     * 关闭数据库连接，并将其返回到连接池中。如果连接已经无效，则会尝试关闭这个无效的连接。
     * 该方法是同步的，以避免多线程环境下的并发问题。
     *
     * @param connection 需要被关闭和可能被回收的数据库连接。
     */
    public synchronized void closeConnection(Connection connection) {
        // 判断传入的连接是否有效
        if (isValidConnection(connection)) {
            // 如果连接有效，则将其放入初始化池，同时从使用池中移除
            initPool.offer(connection);
            usePool.remove(connection);
        } else {
            // 如果连接无效，则调用方法关闭这个无效的连接
            closeInvalidConnection(connection);
        }
        // 唤醒所有等待该对象的线程，以处理可能的资源竞争
        this.notifyAll();
    }


    private void closeInvalidConnection(Connection connection) {
        try {
            connection.close();
            System.out.println("关闭了一个无效的数据库连接");
        } catch (SQLException e) {
            System.out.println("关闭无效连接时出现问题: " + e.getMessage());
        }
    }

    private boolean isValidConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("检查数据库连接有效性时发生异常: " + e.getMessage());
        }
        return false;
    }

    private static class Builder {
        private static final ConnectPoolUtil CONNECT_POOL_UTIL = new ConnectPoolUtil();
    }

    public static ConnectPoolUtil getInstance() {
        return Builder.CONNECT_POOL_UTIL;
    }
}
