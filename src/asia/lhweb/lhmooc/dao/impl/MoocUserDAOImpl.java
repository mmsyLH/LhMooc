package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.MoocUserDAO;
import asia.lhweb.lhmooc.model.bean.MoocUser;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class MoocUserDAOImpl extends BasicDAO<MoocUser> implements MoocUserDAO {

    /**
     * 按用户名获取
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public MoocUser getByUsername(String username) {
        String sql = "SELECT * FROM mooc_user WHERE username=?";
        return selectOne(MoocUser.class, sql, username);
    }

    /**
     * 保存
     *
     * @param moocUser 蕴藏用户
     * @return int
     */
    @Override
    public int add(MoocUser moocUser) {
        String sql = "INSERT INTO mooc_user (username,password) VALUES(?,?)";
        return DML(sql, moocUser.getUsername(), moocUser.getPassword());
    }
}
