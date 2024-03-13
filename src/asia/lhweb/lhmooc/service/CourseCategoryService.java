package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseCategory;

/**
 * 课程分类服务
 *
 * @author Administrator
 * @description 针对表【course_category(课程分类表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseCategoryService {

    /**
     * 页面
     *
     * @param parseInt 解析int
     * @param pageSize 页面大小
     * @return {@link Page}<{@link CourseCategory}>
     */
    Page<CourseCategory> page(int parseInt, int pageSize);

    /**
     * 更新
     *
     * @param category 类别
     * @return boolean
     */
    boolean update(CourseCategory category);

    /**
     * 删除
     *
     * @param category 类别
     * @return boolean
     */
    boolean delete(CourseCategory category);
}
