package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.FollowCourse;

import java.util.List;

/**
 * 跟踪视频服务
 *
 * @author Administrator
 * @description 针对表【follow_Course(视频收藏表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface FollowCourseDAO {

    int getTotalRowAnd(FollowCourse followCourse);

    /**
     * 选择所有
     *
     * @param followCourse 遵循课程
     * @return {@link List}<{@link FollowCourse}>
     */
    List<FollowCourse> selectAll(FollowCourse followCourse);
}
