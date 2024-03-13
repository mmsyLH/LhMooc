package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.annotation.Id;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.utils.JDBCUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基本DAO
 *
 * @author 罗汉
 * @date 2024/03/11
 */
public class BasicDAO<T> {
    private Connection conn;
    private PreparedStatement pstat = null;
    private ResultSet res = null;

    /**
     * 查询表中所有数据
     *
     * @param t 泛型参数，表示查询的表对应的实体类
     * @return 返回查询结果，以List形式返回
     */
    public List<T> selectAll(T t) {
        // 定义SQL语句
        String sql = "select * from ";
        // 获取泛型参数的Class对象
        Class clazz = t.getClass();
        String tableName = getTableName(clazz);
        // 拼接SQL语句
        sql += tableName.toLowerCase(); // 全部转小写

        // 动态的查询语句
        String whereClause = generateWhereClause(t);
        if (!whereClause.isEmpty()) {
            sql += " where " + whereClause;
        }
        System.out.println(sql);
        // 定义结果集合
        List<T> list = new ArrayList<T>();
        // 获取实体类中的所有属性
        Field[] field = clazz.getDeclaredFields();
        try {
            // 获取数据库连接
            conn = JDBCUtils.getConnection();
            // 创建PreparedStatement对象
            pstat = conn.prepareStatement(sql);

            // 设置动态生成的where子句中的参数值
            setWhereClauseParameters(pstat, t);

            // 执行SQL语句，获取ResultSet对象
            res = pstat.executeQuery();

            // 遍历ResultSet对象
            while (res.next()) {
                // 创建实体类对象
                T e = (T) clazz.newInstance();
                // 遍历实体类中的所有属性
                for (int j = 1; j <= field.length; j++) {
                    // 获取属性对象
                    Field field1 = field[j - 1];
                    // 根据属性名获取属性的PropertyDescriptor对象
                    PropertyDescriptor pd = new PropertyDescriptor(field1.getName(), clazz);
                    // 获取属性的setter方法
                    Method setMethod = pd.getWriteMethod();
                    // System.out.println(setMethod);
                    // 根据属性类型设置属性值
                    if (field1.getType() == String.class) {
                        setMethod.invoke(e, res.getString(j));
                    } else if (field1.getType() == Integer.class) {
                        setMethod.invoke(e, res.getInt(j));
                    } else if (field1.getType() == Double.class) {
                        setMethod.invoke(e, res.getDouble(j));
                    } else if (field1.getType() == java.util.Date.class) {
                        setMethod.invoke(e, res.getObject(j));
                    }
                }
                // 将实体类对象添加到结果集合中
                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    /**
     * 真实删除单个记录
     *
     * @param t t
     * @return int
     */
    public int deleteOne(T t) {
        Class clazz = t.getClass();
        // 获取表名
        String tableName = getTableName(clazz);
        StringBuilder stringBuilder = new StringBuilder("delete from ");
        // 拼接查询语句
        stringBuilder.append(tableName).append(" where ");

        try {
            // 获取主键
            Field primaryKeyField = getPrimaryKeyField(clazz);

            // 拼接主键和值
            if (primaryKeyField != null) {
                primaryKeyField.setAccessible(true);
                String name = primaryKeyField.getName();
                Object o = primaryKeyField.get(t);// 这个坑！！！注意不是传claszz而是传t
                stringBuilder.append(name).append(" = ").append(o.toString());
            } else {
                return -1;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return DML(stringBuilder.toString());
    }

    /**
     * 逻辑删除单个记录
     *
     * @param t t
     * @return int
     */
    public int delete(T t) {
        Class clazz = t.getClass();
        // 获取表名
        String tableName = getTableName(clazz);
        StringBuilder stringBuilder = new StringBuilder("update ");
        // 拼接查询语句
        stringBuilder.append(tableName).append(" set isdelete=1").append(" where ");
        try {
            // 获取主键
            Field primaryKeyField = getPrimaryKeyField(clazz);

            // 拼接主键和值
            if (primaryKeyField != null) {
                primaryKeyField.setAccessible(true);
                String name = primaryKeyField.getName();
                Object o = primaryKeyField.get(t);// 这个坑！！！注意不是传claszz而是传t
                stringBuilder.append(name).append(" = ").append(o.toString());
            } else {
                return -1;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return DML(stringBuilder.toString());
    }

    /**
     * 根据主键更新更新
     *
     * @param t t
     * @return int
     */
    public int update(T t) {
        Class clazz = t.getClass();
        conn = null;
        pstat = null;
        res = null;
        // 获取表名
        String tableName = getTableName(clazz);
        // 构建更新语句
        StringBuilder sqlBuilder = new StringBuilder("update ");
        sqlBuilder.append(tableName).append(" set ");
        // 获取实体类中的所有属性
        Field[] fields = clazz.getDeclaredFields();
        List<Object> params = new ArrayList<>();
        try {
            boolean firstField = true;
            for (Field field : fields) {
                // 获取属性值
                field.setAccessible(true);
                Object value = field.get(t);
                // 如果属性值不为空，将其添加到更新语句中
                if (value != null) {
                    if (!firstField) {
                        sqlBuilder.append(", ");
                    }
                    sqlBuilder.append(field.getName()).append(" = ?");
                    params.add(value);
                    firstField = false;
                }
            }
            // 添加更新条件（更新条件为主键）
            sqlBuilder.append(" where ");
            // 获取主键属性
            Field primaryKeyField = getPrimaryKeyField(clazz);
            if (primaryKeyField != null) {
                primaryKeyField.setAccessible(true);
                Object primaryKeyValue = primaryKeyField.get(t);
                sqlBuilder.append(primaryKeyField.getName()).append(" = ?");
                params.add(primaryKeyValue);
            } else {
                throw new SQLException("实体表内没有主键" + clazz.getName());
            }
            // 执行增删改查操作
            return DML(sqlBuilder.toString(), params.toArray());
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstat, res);
        }
        return -1;
    }

    /**
     * 保存在数据库中
     *
     * @param t 需要保存的类型
     * @return int
     */
    public int save(T t) {
        Class clazz = t.getClass();
        conn = null;
        pstat = null;
        res = null;
        // 获取表名
        String tableName = getTableName(clazz);
        // 构建更新语句
        StringBuilder sqlBuilder = new StringBuilder("insert into ");
        sqlBuilder.append(tableName).append("(");
        // 获取实体类中的所有属性
        Field[] fields = clazz.getDeclaredFields();
        List<Object> params = new ArrayList<>();
        // todo 拼接各种参数 和?
        //
        return -1;
    }


    /**
     * 获取总行
     *
     * @param t t
     * @return int
     */
    public int getTotalRow(T t) {
        Class clazz = t.getClass();
        String tableName = getTableName(clazz);
        try {
            String sql = "SELECT COUNT(*) from " + tableName;
//			System.out.println(sql);
            conn = JDBCUtils.getConnection();
            pstat = conn.prepareStatement(sql);
            res = pstat.executeQuery();
            while (res.next()) {
                String rows = res.getString("COUNT(*)");
                return Integer.parseInt(rows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 用于从数据库中分页获取某个表中的数据
     *
     * @param t        泛型对象，可以是任意表对应的JavaBean对象
     * @param begin    开始索引
     * @param pageSize 每页数据条数
     * @return 返回一个List对象，包含从数据库中分页获取的数据
     */
    public List<T> getPageItems(T t, int begin, int pageSize) {
        // 获取泛型对象的Class对象
        Class clazz = t.getClass();
        String tableName = getTableName(clazz);
        // 构建查询语句
        String sql = "SELECT * FROM " + tableName + " product LIMIT ?,?";
        // 获取数据库连接
        try {
            conn = JDBCUtils.getConnection();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        // 创建一个List对象，用于保存从数据库中查询出的数据
        List<T> list = new ArrayList<T>();
        // 获取泛型对象中的所有属性
        Field[] field = clazz.getDeclaredFields();
        try {
            // 准备查询语句
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, begin);
            pstat.setInt(2, pageSize);
            // 执行查询语句
            res = pstat.executeQuery();
            while (res.next()) {
                // 创建一个泛型对象的实例
                T e = (T) clazz.newInstance();
                // 遍历对象中的所有属性
                for (int j = 1; j <= field.length; j++) {
                    Field field1 = field[j - 1];
                    // 获取属性对应的setter方法
                    PropertyDescriptor pd = new PropertyDescriptor(field1.getName(), clazz);
                    Method setMethod = pd.getWriteMethod();
                    // 根据属性的类型，调用setter方法设置属性的值
                    if (field1.getType() == String.class) {
                        setMethod.invoke(e, res.getString(j));
                    } else if (field1.getType() == Integer.class) {
                        setMethod.invoke(e, res.getInt(j));
                    } else if (field1.getType() == Double.class) {
                        setMethod.invoke(e, res.getDouble(j));
                    } else if (field1.getType() == Date.class) {
                        setMethod.invoke(e, res.getDate(j));
                    }
                }
                // 将获取到的数据添加到List中
                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回从数据库中分页获取的数据
        return list;
    }

    /**
     * 分页
     *
     * @param t        t
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @return Page<T>
     */
    public Page<T> page(T t, int pageNo, int pageSize) {
        // 先创建一个Page对象，然后根据实际情况填充属性
        Page<T> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = getTotalRow(t);
        page.setTotalRow(totalRow);
        // pageTotalCount 是计算得到-> 一个小小的算法
        // 比如 6 2 =》 6 / 2 = 3
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            pageTotalCount += 1;
        }
        page.setPageTotalCount(pageTotalCount);
        // private List<T> items
        // 验证: pageNo = 1 pageSize = 3 => begin =0
        // OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin = (pageNo - 1) * pageSize;
        List<T> pageItems = getPageItems(t, begin, pageSize);
        page.setItems(pageItems);
        return page;
    }

    /**
     * 通过id去查询单个记录
     *
     * @param t t
     * @return {@link T}
     */
    public <T> T selectOneById(T t) {

        Class clazz = t.getClass();
        // 获取表名
        String tableName = getTableName(clazz);
        StringBuilder stringBuilder = new StringBuilder("SELECT * from ");
        // 拼接查询语句
        stringBuilder.append(tableName).append(" where ");
        Field[] fields = clazz.getDeclaredFields();
        try {
            // 获取主键
            Field primaryKeyField = getPrimaryKeyField(clazz);
            // 拼接主键和值
            if (primaryKeyField != null) {
                primaryKeyField.setAccessible(true);
                String name = primaryKeyField.getName();
                Object o = primaryKeyField.get(t);// 这个坑！！！注意不是传claszz而是传t
                stringBuilder.append(name).append(" = ").append(o.toString());
            } else {
                return null;
            }
            String sql = stringBuilder.toString();
            conn = JDBCUtils.getConnection();
            pstat = conn.prepareStatement(sql);
            res = pstat.executeQuery();
            // 遍历结果集
            if (res.next()) {
                // 创建实体类对象
                T e = (T) clazz.newInstance();
                for (int j = 1; j <= fields.length; j++) {
                    Field field1 = fields[j - 1];
                    // 获取属性对应的setter方法
                    PropertyDescriptor pd = new PropertyDescriptor(field1.getName(), clazz);
                    Method setMethod = pd.getWriteMethod();
                    // 根据属性的类型，调用setter方法设置属性的值
                    if (field1.getType() == String.class) {
                        setMethod.invoke(e, res.getString(j));
                    } else if (field1.getType() == Integer.class) {
                        setMethod.invoke(e, res.getInt(j));
                    } else if (field1.getType() == Double.class) {
                        setMethod.invoke(e, res.getDouble(j));
                    } else if (field1.getType() == Date.class) {
                        setMethod.invoke(e, res.getDate(j));
                    }
                }
                return e;
            }
        } catch (IllegalAccessException | SQLException | InstantiationException | IntrospectionException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 查询单个
     *
     * @param clazz clazz
     * @param sql   sql
     * @param args  arg游戏
     * @return {@link T}
     */
    public <T> T selectOne(Class<T> clazz, String sql, Object... args) {
        try {
            // 获取数据连接
            conn = JDBCUtils.getConnection();
            // 获取预编译语句对象
            pstat = conn.prepareStatement(sql);
            // 填充属性值，注意下标从 1 开始
            for (int i = 0; i < args.length; i++) {
                pstat.setObject(i + 1, args[i]);
            }
            // 执行查询，获取结果集
            res = pstat.executeQuery();
            // 获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = res.getMetaData();
            // 通过元数据获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            // rs.next()方法判断是否存在下一条，相当于集合迭代器的 hasNext()
            if (res.next()) {
                // 实体类必须包含空参构造器，才可以正常执行 newInstance()
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取别名（getColumnName()是获取列名，不建议使用）
//					String columnLabel = rsmd.getColumnLabel(i + 1);
                    String columnLabel = rsmd.getColumnName(i + 1);
                    // 获取列值
                    Object columnVal = res.getObject(i + 1);
                    // 通过反射封装对象
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstat, res);
        }
        return null;
    }

    /**
     * 真正的DML方法
     *
     * @param sql 传入完整的可执行sql语句
     * @return int
     */
    public int DML(String sql) {
        conn = null;
        pstat = null;
        try {
            conn = JDBCUtils.getConnection();
            pstat = conn.prepareStatement(sql);
            return pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstat);
        }
        return -1;
    }

    /**
     * 真正的DML方法
     *
     * @param sql    sql
     * @param params 参数个数
     * @return int
     */
    public int DML(String sql, Object... params) {
        conn = null;
        pstat = null;
        try {
            conn = JDBCUtils.getConnection();
            pstat = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstat.setObject(i + 1, params[i]);
                }
            }
            return pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstat);
        }
        return -1;
    }


    /**
     * 获取表名
     *
     * @param clazz clazz
     * @return {@link String}
     */
    private String getTableName(Class clazz) {
        // 获取类名
        String clazzSimpleName = clazz.getSimpleName();// CommentVideo
        // 转换类名到表名（假设驼峰命名法）
        String tableName = clazzSimpleName.replaceAll("(?<!^)([A-Z])", "_$1").toLowerCase();
        return tableName;
    }

    /**
     * 生成动态的where子句
     *
     * @param t t
     * @return {@link String}
     */
    private String generateWhereClause(T t) {
        StringBuilder whereClause = new StringBuilder();
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value != null) {
                    if (whereClause.length() > 0) {
                        whereClause.append(" and ");
                    }
                    whereClause.append(field.getName()).append(" = ?");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return whereClause.toString();
    }

    /**
     * 设置where子句参数
     *
     * @param pstat pstat
     * @param t     t
     * @throws SQLException sqlexception异常
     */
    private void setWhereClauseParameters(PreparedStatement pstat, T t) throws SQLException {
        Field[] fields = t.getClass().getDeclaredFields();
        int parameterIndex = 1;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value != null) {
                    pstat.setObject(parameterIndex++, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取主键字段
     *
     * @param clazz clazz
     * @return {@link Field}
     */
    private Field getPrimaryKeyField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) { //@Id 注解标记主键字段
                return field;
            }
        }
        return null;
    }
}
