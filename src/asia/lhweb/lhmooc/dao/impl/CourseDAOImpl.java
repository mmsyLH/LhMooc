package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.CourseDAO;
import asia.lhweb.lhmooc.model.bean.Course;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class CourseDAOImpl extends BasicDAO<Course> implements CourseDAO {

    /**
     * 根据id查询
     *
     * @param course 课程
     * @return {@link Course}
     */
    @Override
    public Course selectOneById(Course course) {
        return super.selectOneById(course);
    }
}
