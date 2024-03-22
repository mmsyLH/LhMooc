package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseVideo;

import java.util.List;

/**
 * 课程视频服务
 *
 * @author Administrator
 * @description 针对表【course_video】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseVideoDAO {

    /**
     * 选择所有
     *
     * @param courseVideo 课程视频
     * @return {@link List}<{@link CourseVideo}>
     */
    List<CourseVideo> selectAll(CourseVideo courseVideo);

    /**
     * 按id选择一个
     *
     * @param courseVideoTemp 课程视频临时
     * @return {@link CourseVideo}
     */
    CourseVideo selectOneById(CourseVideo courseVideoTemp);


    /**
     * 真正删除
     *
     * @param courseVideo 课程视频
     * @return int
     */
    int realDelete(CourseVideo courseVideo);

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
     * 保存
     *
     * @param courseVideo 课程视频
     * @return int
     */
    int save(CourseVideo courseVideo);

    /**
     * 更新
     *
     * @param findCourseVideo 查找课程视频
     * @return int
     */
    int update(CourseVideo findCourseVideo);
}
