package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.*;
import asia.lhweb.lhmooc.dao.impl.*;
import asia.lhweb.lhmooc.model.bean.*;
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
    private CourseVideoDAO courseVideoDAO = new CourseVideoDAOImpl();
    // 课程章节dao
    private CourseChapterDAO courseChapterDAO = new CourseChapterDAOImpl();
    // 课程dao
    private CourseDAO courseDAO = new CourseDAOImpl();
    // 课程分类dao
    private CourseCategoryDAO courseCategoryDAO = new CourseCategoryDAOImpl();

    /**
     * 通过用户id和视频名称获取
     * 通过用户id和视频名称获取个人历史播放记录
     *
     * @param userId    用户id
     * @param videoName 视频名字
     * @return {@link Result}
     */
    @Override
    public Result getByUserIdAndVideoName(int userId, String videoName) {
        PlayHistory playHistory = new PlayHistory();
        playHistory.setUserid(userId);

        // if (!"".equals(videoName)){
        //     playHistory.setVideoid(courseVideoDAO.selectOneById(new CourseVideo(videoName)).getVideoid());
        // }

        // 根据视频名称查看用户播放历史
        List<PlayHistory> myPlayHistoryList = playHistoryDAO.selectAll(playHistory);
        if (myPlayHistoryList.isEmpty()) {
            return Result.error("查询的个人播放历史为空");
        }

        List<PlayHistoryVo> playHistoryVoList = new ArrayList<>();
        PlayHistoryVo playHistoryVoTemp;
        CourseVideo courseVideoTemp;
        CourseChapter courseChapterTemp;
        Course courseTemp;
        CourseCategory courseCategoryTemp;
        for (PlayHistory history : myPlayHistoryList) {
            playHistoryVoTemp = new PlayHistoryVo();
            DataUtils.copyNonNullProperties(history, playHistoryVoTemp);
            // 属性拷贝完后进行查询对应数据进行填充

            // 获取视频名、视频时长、视频url
            courseVideoTemp = new CourseVideo();
            courseVideoTemp.setVideoid(playHistoryVoTemp.getVideoid());
            courseVideoTemp = courseVideoDAO.selectOneById(courseVideoTemp);
            playHistoryVoTemp.setVideoName(courseVideoTemp.getVideoname());// 视频名称
            playHistoryVoTemp.setVideoUrl(courseVideoTemp.getVideourl());// 视频url
            playHistoryVoTemp.setVideoTime(courseVideoTemp.getVideotime());// 视频时间
            // playHistoryVoTemp.setC(courseVideoTemp.getChapterid());//课程id

            // 获取章节名
            courseChapterTemp = new CourseChapter();
            courseChapterTemp.setChapterid(courseVideoTemp.getChapterid());
            courseChapterTemp = courseChapterDAO.selectOneById(courseChapterTemp);
            playHistoryVoTemp.setCourChapterName(courseChapterTemp.getChaptername());//设置章节名

            // 获取课程名、课程简介、
            courseTemp=new Course();
            courseTemp.setCourseid(courseChapterTemp.getCouseid());
            courseTemp = courseDAO.selectOneById(courseTemp);
            playHistoryVoTemp.setCourseName(courseTemp.getCoursename());//设置课程名
            playHistoryVoTemp.setCourseProfile(courseTemp.getProfile());//设置课程简介


            //获取课程分类名  todo 统计该课程下一共多少个视频 暂时先前端统计
            courseCategoryTemp=new CourseCategory();
            courseCategoryTemp.setCategoryid(courseTemp.getCategoryid());
            courseCategoryTemp = courseCategoryDAO.selectOneById(courseCategoryTemp);
            playHistoryVoTemp.setCourCategoryName(courseCategoryTemp.getCategoryname());//设置课程分类名

            playHistoryVoList.add(playHistoryVoTemp);
        }

        return Result.success(playHistoryVoList,"查询个人播放记录成功！");
    }
}
