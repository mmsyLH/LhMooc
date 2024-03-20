package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.MoocUserDAO;
import asia.lhweb.lhmooc.dao.TransactionDAO;
import asia.lhweb.lhmooc.dao.impl.MoocUserDAOImpl;
import asia.lhweb.lhmooc.dao.impl.TransactionDAOImpl;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import asia.lhweb.lhmooc.model.bean.Transaction;
import asia.lhweb.lhmooc.service.MoocUserService;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

/**
 * 用户服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class MoocUserServiceImpl implements MoocUserService {
    private MoocUserDAO moocUserDAO = new MoocUserDAOImpl();

   //支付流水Dao
    private TransactionDAO transactionDAO = new TransactionDAOImpl();
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
        boolean exists = moocUserDAO.getByUsername(username) != null;
        ;
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

    /**
     * 页面
     * 分页
     *
     * @param moocUser
     * @param parseInt 解析int
     * @param pageSize 页面大小
     * @return {@link Page}<{@link MoocUser}>
     */
    @Override
    public Page<MoocUser> page(MoocUser moocUser, int parseInt, int pageSize) {
        return moocUserDAO.page(moocUser, parseInt, pageSize);
    }

    /**
     * 更新
     *
     * @param moocUser 蕴藏用户
     * @return boolean
     */
    @Override
    public boolean update(MoocUser moocUser) {
        return moocUserDAO.update(moocUser) != -1;
    }

    /**
     * 删除
     *
     * @param moocUser 蕴藏用户
     * @return boolean
     */
    @Override
    public boolean delete(MoocUser moocUser) {

        return moocUserDAO.deleteByid(moocUser) != -1;
    }

    /**
     * 通过id获取用户
     *
     * @param userId 用户id
     * @return {@link Result}<{@link MoocUser}>
     */
    @Override
    public Result<MoocUser> getUserById(int userId) {
        MoocUser moocUserTest = new MoocUser();
        moocUserTest.setId(userId);
        MoocUser moocUser = moocUserDAO.selectOneById(moocUserTest);
        if (moocUser == null) {
            return Result.error("用户不存在");
        }
        if (moocUser.getIsdelete() == 1) {
            return Result.error("用户已被删除");
        }
        return Result.success(moocUser, "查询成功");
    }

    /**
     * 根据传入的cookies字符串解析出MoocUser对象。
     *
     * @param cookies 用户的cookie字符串，格式为key=value;key=value。
     * @param gson    用于将json字符串转换为MoocUser对象的Gson实例。
     * @return 如果找到LoginMoocUser字段并成功解析，则返回对应的MoocUser对象；否则返回null。
     */
    @Override
    public MoocUser parseCookieToMoocUser(String cookies, Gson gson) {
        // 将cookies字符串按分号分割，逐个查找LoginMoocUser字段
        String[] cookieParts = cookies.split(";");
        for (String cookiePart : cookieParts) {
            // 分割key和value
            String[] keyValue = cookiePart.trim().split("=");
            if (keyValue.length == 2 && keyValue[0].equals("LoginMoocUser")) {
                // 找到LoginMoocUser字段，将其value解析为MoocUser对象
                String json = keyValue[1];
                return gson.fromJson(json, MoocUser.class);
            }
        }
        // 未找到LoginMoocUser字段，返回null
        return null;
    }

    /**
     * 检查用户权限
     *
     * @param userId     用户id
     * @param cookieUser 饼干用户
     * @return boolean
     */
    @Override
    public boolean checkUserPermission(String userId, MoocUser cookieUser) {
        if (cookieUser == null) {
            return false;
        }
        // 检查是否是管理员或账户本人
        return cookieUser.getId() == Integer.parseInt(userId) || cookieUser.getUserrole() == 1;
    }

    /**
     * 用户更新通过id
     *
     * @param moocUser 蕴藏用户
     * @return {@link Result}
     */
    @Override
    public Result userUpdate(MoocUser moocUser) {
        MoocUser findMoocUser = moocUserDAO.selectOneById(moocUser);
        if (findMoocUser == null) return Result.error("更新失败，用户不存在");

        if (moocUserDAO.update(moocUser) != -1) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 充电
     *
     * @param userId 用户id
     * @param money  需要充值的钱
     * @return {@link Result}
     */
    @Override
    public Result recharge(Integer userId, Double money) {
        //todo 事务
        MoocUser moocUser = new MoocUser();
        moocUser.setId(userId);
        MoocUser findMoocUser = moocUserDAO.selectOneById(moocUser);
        if (findMoocUser == null) return Result.error("充值失败，用户不存在");

        findMoocUser.setWallet(findMoocUser.getWallet() + money);
        if (moocUserDAO.update(findMoocUser) != -1) {
            Transaction transaction = new Transaction();
            transaction.setTransactionid(findMoocUser.getUsername()+DataUtils.getCode());
            transaction.setUserid(userId);
            transaction.setTransactionstatus(1);//1 成功 2失败 3进行中
            transaction.setAmount(money);
            transaction.setTransactiontype(1);// 1 充值 2消费
            transaction.setInfo("用户充值");
            if ( transactionDAO.save(transaction)!=-1){
                return Result.success("充值成功");
            }else {
                return Result.error("充值失败");
            }
        } else {
            return Result.error("充值失败");
        }
    }

    /**
     * 是我还是管理员?
     *
     * @param req    要求事情
     * @param resp   分别地
     * @param userId 用户id
     * @param gson   gson
     * @return boolean
     */
    @Override
    public boolean isNoMeOrAdmin(LhRequest req, LhResponse resp, String userId, Gson gson) {
        // 从携带的请求中获取用户信息
        String cookies = req.getHeardParameter("Cookie");

        // 验证权限
        // 1得到cookie中的用户信息
        MoocUser cookieUser = parseCookieToMoocUser(cookies, gson);
        // 2调用方法
        if (!checkUserPermission(userId, cookieUser)) {
            resp.writeToJson(gson.toJson(Result.error("权限验证失败 您不是账户本人或者管理员")));
            return true;
        }
        // 有权限后再进行业务逻辑
        return false;
    }
}
