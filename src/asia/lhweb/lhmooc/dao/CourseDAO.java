package asia.lhweb.lhmooc.dao;

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
    List<Course> selectAll();
}
