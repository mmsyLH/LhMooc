package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.LikeCourseDAO;
import asia.lhweb.lhmooc.model.bean.LikeCourse;

import java.util.List;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class LikeCourseDAOImpl extends BasicDAO<LikeCourse> implements LikeCourseDAO {
    /**
     * 选择所有
     *
     * @return {@link List}<{@link LikeCourse}>
     */
    @Override
    public List<LikeCourse> selectAll() {
        return selectAll(new LikeCourse());
    }
}
