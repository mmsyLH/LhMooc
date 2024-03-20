package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.PlayHistory;

import java.util.List;

/**
 * 播放历史服务
 *
 * @author Administrator
 * @description 针对表【play_history】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface PlayHistoryDAO {

    /**
     * 选择所有
     *
     * @param playHistory 个人播放历史
     * @return {@link List}<{@link PlayHistory}>
     */
    List<PlayHistory> selectAll(PlayHistory playHistory);
}
