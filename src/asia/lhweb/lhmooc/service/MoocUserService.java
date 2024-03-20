package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.MoocUser;
import com.google.gson.Gson;

/**
 * Mooc用户服务
 *
 * @author Administrator
 * @description 针对表【mooc_user(慕课用户信息表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface MoocUserService{

    /**
     * 注册
     *
     * @param moocUser 蕴藏用户
     * @return boolean
     */
    boolean register(MoocUser moocUser);

    /**
     * 是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    boolean isExists(String username);

    /**
     * 登录
     *
     * @param moocUser 蕴藏用户
     * @return {@link MoocUser}
     */
    MoocUser login(MoocUser moocUser);

    /**
     * 页面
     * 分页
     *
     * @param moocUser
     * @param parseInt 解析int
     * @param pageSize 页面大小
     * @return {@link Page}<{@link MoocUser}>
     */
    Page<MoocUser> page(MoocUser moocUser, int parseInt, int pageSize);

    /**
     * 更新
     *
     * @param moocUser 蕴藏用户
     * @return boolean
     */
    boolean update(MoocUser moocUser);

    /**
     * 删除
     *
     * @param moocUser 蕴藏用户
     * @return boolean
     */
    boolean delete(MoocUser moocUser);

    /**
     * 通过id获取用户
     *
     * @param parseInt 解析int
     * @return {@link Result}<{@link MoocUser}>
     */
    Result<MoocUser> getUserById(int parseInt);

    /**
     * 解析cookie到mooc用户
     *
     * @param cookies 饼干
     * @param gson    gson
     * @return {@link MoocUser}
     */
    MoocUser parseCookieToMoocUser(String cookies, Gson gson);

    /**
     * 检查用户权限
     *
     * @param userId     用户id
     * @param cookieUser 饼干用户
     * @return boolean
     */
    boolean checkUserPermission(String userId, MoocUser cookieUser);

    /**
     * 用户更新通过id
     *
     * @param moocUser 蕴藏用户
     * @return {@link Result}
     */
    Result userUpdate(MoocUser moocUser);

    Result recharge(Integer parseInt, Double parseDouble);

    /**
     * 不是我还是管理员
     *
     * @param req    要求事情
     * @param resp   分别地
     * @param userId 用户id
     * @param gson   gson
     * @return boolean
     */
    boolean isNoMeOrAdmin(LhRequest req, LhResponse resp, String userId, Gson gson);
}
