package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.CourseDAO;
import asia.lhweb.lhmooc.model.bean.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class CourseDAOImpl extends BasicDAO<Course> implements CourseDAO {
    /**
     * 选择所有
     *
     * @return {@link ArrayList}<{@link Course}>
     */
    @Override
    public List<Course> selectAll() {
        return selectAll(new Course());
    }
}
