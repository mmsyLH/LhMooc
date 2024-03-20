package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.CourseChapterDAO;
import asia.lhweb.lhmooc.dao.CourseVideoDAO;
import asia.lhweb.lhmooc.dao.PlayHistoryDAO;
import asia.lhweb.lhmooc.dao.impl.CourseChapterDAOImpl;
import asia.lhweb.lhmooc.dao.impl.CourseVideoDAOImpl;
import asia.lhweb.lhmooc.dao.impl.PlayHistoryDAOImpl;
import asia.lhweb.lhmooc.model.bean.PlayHistory;
import asia.lhweb.lhmooc.model.vo.PlayHistoryVo;
import asia.lhweb.lhmooc.service.PlayHistoryService;
import asia.lhweb.lhmooc.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付历史服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class PlayHistoryServiceImpl implements PlayHistoryService {
    // 播放历史dao
    private PlayHistoryDAO playHistoryDAO = new PlayHistoryDAOImpl();
    // 课程视频dao
    private CourseVideoDAO courseVideo = new CourseVideoDAOImpl();
    // 课程章节dao
    private CourseChapterDAO courseChapterDAO = new CourseChapterDAOImpl();
    // 课程dao

    /**
     * 通过用户id和视频名称获取个人历史播放记录
     *
     * @param videoName 视频名字
     * @param userId    用户id
     * @return {@link Result}
     */
    @Override
    public Result getByUserIdAndVideoName(int userId, String videoName) {
        PlayHistory playHistory = new PlayHistory();
        playHistory.setUserid(userId);
        List<PlayHistory> myPlayHistoryList = playHistoryDAO.selectAll(playHistory);

        List<PlayHistoryVo> playHistoryVoList = new ArrayList<>();
        PlayHistoryVo playHistoryVoTemp;
        for (PlayHistory history : myPlayHistoryList) {
            playHistoryVoTemp = new PlayHistoryVo();
            DataUtils.copyNonNullProperties(history, playHistoryVoTemp);
            // 属性拷贝完后进行查询对应数据进行填充


            playHistoryVoList.add(playHistoryVoTemp);
        }


        return null;
    }
}
