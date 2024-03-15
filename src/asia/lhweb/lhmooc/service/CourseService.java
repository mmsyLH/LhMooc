package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.model.bean.Course;

import java.util.List;

/**
 * 课程服务
 *
 * @author Administrator
 * @description 针对表【course(课程表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseService {

    /**
     * 获得排名top8课程
     *
     * @param sort 排序方式
     * @return {@link List}<{@link Course}>
     */
    List<Course> getSortCoursesTop8(String sort);


    /**
     * 按类别获取
     *
     * @param course 课程
     * @return {@link List}<{@link Course}>
     */
    List<Course> getByCategory(Course course);

    /**
     * 添加
     *
     * @param course 课程
     * @return boolean
     */
    boolean add(Course course);
}
