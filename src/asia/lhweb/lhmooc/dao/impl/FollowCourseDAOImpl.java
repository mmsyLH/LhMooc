package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.FollowCourseDAO;
import asia.lhweb.lhmooc.model.bean.FollowCourse;

import java.util.List;

/**
 * 收藏视频
 *
 * @author 罗汉
 * @date 2024/03/13
 */
public class FollowCourseDAOImpl extends BasicDAO<FollowCourse> implements FollowCourseDAO {
    /**
     * 选择所有
     *
     * @return {@link List}<{@link FollowCourse}>
     */
    @Override
    public List<FollowCourse> selectAll() {
        return selectAll(new FollowCourse());
    }
}
