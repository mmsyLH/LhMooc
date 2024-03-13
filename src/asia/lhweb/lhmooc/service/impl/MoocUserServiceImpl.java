package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.MoocUserDAO;
import asia.lhweb.lhmooc.dao.impl.MoocUserDAOImpl;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.utils.DataUtils;

/**
 * 用户服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class MoocUserServiceImpl implements MoocUserService {
    private MoocUserDAO moocUserDAO = new MoocUserDAOImpl();

    /**
     * 注册
     *
     * @param moocUser 蕴藏用户
     * @return boolean
     */
    @Override
    public boolean register(MoocUser moocUser) {
        // 多重判断 保险！！！
        // 1 用户名效验
        String username = moocUser.getUsername();
        if (username == null || username.trim().isEmpty()) {

            return false;
        }

        // 2 密码效验
        String password = moocUser.getPassword();
        if (password == null || password.length() < 4) {

            return false;
        }

        // 3 用户名是否存在效验
        boolean exists = moocUserDAO.getByUsername(username) != null;;
        if (exists) {
            return false;
        }
        // MD5加密
        moocUser.setPassword(DataUtils.encryptPassword(moocUser.getPassword()));

        return moocUserDAO.add(moocUser) != -1;
    }



    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean isExists(String username) {
        return moocUserDAO.getByUsername(username) != null;
    }

    /**
     * 登录
     *
     * @param moocUser 蕴藏用户
     * @return {@link MoocUser}
     */
    @Override
    public MoocUser login(MoocUser moocUser) {
        String username = moocUser.getUsername();
        String password = moocUser.getPassword();
        MoocUser user = moocUserDAO.getByUsername(username);
        if (user == null) {
            return null;
        }
        if (!DataUtils.verifyPassword(password, user.getPassword())) {
            return null;
        }
        return user;
    }
}
