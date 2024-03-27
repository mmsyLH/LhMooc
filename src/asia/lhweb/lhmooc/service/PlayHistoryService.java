package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.common.Result;

/**
 * 播放历史服务
 *
 * @author Administrator
 * @description 针对表【play_history】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface PlayHistoryService {

    /**
     * 通过用户id和视频名称获取
     * 通过用户id和视频名称获取个人历史播放记录
     *
     * @param userId 用户id
     * @return {@link Result}
     */
    Result getByUserId(int userId);

    /**
     * 添加游戏记录
     *
     * @param parseInt  解析int
     * @param parseInt1 解析int1
     * @return {@link Result}
     */
    Result addPlayHistory(int parseInt, int parseInt1);
}
