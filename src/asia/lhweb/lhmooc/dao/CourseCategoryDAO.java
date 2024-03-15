package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseCategory;

import java.util.List;

/**
 * 课程分类服务
 *
 * @author Administrator
 * @description 针对表【course_category(课程分类表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseCategoryDAO {

    /**
     * 分类分页
     *
     * @param parseInt 解析int
     * @param pageSize 页面大小
     * @return {@link Page}<{@link CourseCategory}>
     */
    Page<CourseCategory> categoryPage(int parseInt, int pageSize);

    /**
     * 更新
     *
     * @param category 类别
     * @return int
     */
    int update(CourseCategory category);

    /**
     * 通过id逻辑删除删除
     *
     * @param category 类别
     * @return int
     */
    int delete(CourseCategory category);

    /**
     * 真正删除
     *
     * @param category 类别
     * @return int
     */
    int realDelete(CourseCategory category);

    /**
     * 保存
     *
     * @param category 类别
     * @return int
     */
    int save(CourseCategory category);

    /**
     * 选择所有
     *
     * @return {@link List}<{@link CourseCategory}>
     */
    List<CourseCategory> selectAll(CourseCategory courseCategory);
}
