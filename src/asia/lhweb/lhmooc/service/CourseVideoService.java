package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseVideo;

/**
 * 课程视频服务
 *
 * @author Administrator
 * @description 针对表【course_video】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseVideoService {

    /**
     * 真正删除
     *
     * @param CourseVideoId 课程视频编号
     * @return boolean
     */
    boolean realDelete(int CourseVideoId);

    /**
     * 逐页和
     *
     * @param courseVideo 课程视频
     * @param pageNo      页面没有
     * @param pageSize    页面大小
     * @return {@link Page}<{@link CourseVideo}>
     */
    Page<CourseVideo> pageByAnd(CourseVideo courseVideo, int pageNo, int pageSize);

    /**
     * 添加
     *
     * @param courseVideo 课程视频
     * @return {@link Result}
     */
    Result add(CourseVideo courseVideo);

    /**
     * 更新
     *
     * @param videoname videoname
     * @param videourl  videourl
     * @param videoid   videoid
     * @return {@link Result}
     */
    Result update(int videoid, String videoname, String videourl);
}
