package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程DAO
 * 课程服务
 *
 * @author Administrator
 * @description 针对表【course(课程表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseDAO {

    /**
     * 选择所有
     *
     * @return {@link ArrayList}<{@link Course}>
     */
    List<Course> selectAll(Course course);

    /**
     * 保存
     *
     * @param course 课程
     * @return boolean
     */
    int save(Course course);

    /**
     * 根据id查询
     *
     * @param course 课程
     * @return {@link Course}
     */
    Course selectOneById(Course course);

    Page<Course> pageByAnd(Course course, int pageNo, int pageSize);
}
