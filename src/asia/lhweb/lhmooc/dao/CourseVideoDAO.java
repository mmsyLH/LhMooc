package asia.lhweb.lhmooc.dao;

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

    CourseVideo selectOneById(CourseVideo courseVideoTemp);
}
