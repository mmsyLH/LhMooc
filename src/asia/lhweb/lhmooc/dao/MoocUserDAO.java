package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.MoocUser;

/**
 * Mooc用户服务
 *
 * @author Administrator
 * @description 针对表【mooc_user(慕课用户信息表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface MoocUserDAO {

    /**
     * 按用户名获取
     *
     * @param username 用户名
     * @return boolean
     */
    MoocUser getByUsername(String username);

    /**
     * 保存
     *
     * @param moocUser 蕴藏用户
     * @return int
     */
    int add(MoocUser moocUser);
}
