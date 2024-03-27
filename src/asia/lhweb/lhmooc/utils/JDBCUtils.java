package asia.lhweb.lhmooc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBCUtils
 *
 * @author 罗汉
 * @version 1.0
 * @date 2024/02/21
 */
public class JDBCUtils {
    private static ConnectPoolUtil connectionPool;

    static {
        // 初始化连接池
        connectionPool = ConnectPoolUtil.getInstance();
    }

    /**
     * 获取数据库连接
     *
     * @return Connection
     * @throws SQLException SQL异常
     */
    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    /**
     * 设置数据库
     */
    public static void setDatabase(String username, String password, String url) {
        // 这里可以添加设置数据库的逻辑，如果需要的话
    }

    /**
     * 获取实例
     *
     * @return JDBCUtils
     */
    public static JDBCUtils getInstance() {
        // 这里可以添加获取实例的逻辑，如果需要的话
        return new JDBCUtils();
    }
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    /**
     * 关闭资源
     *
     * @param connection 连接
     * @param statement  Statement
     * @param resultSet  结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            connectionPool.closeConnection(connection);
        }
    }

}
