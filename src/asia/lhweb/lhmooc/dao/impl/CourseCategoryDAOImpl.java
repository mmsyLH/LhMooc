package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.CourseCategoryDAO;
import asia.lhweb.lhmooc.factory.BeanFactory;
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
public class CourseCategoryDAOImpl extends BasicDAO<CourseCategory> implements CourseCategoryDAO {

    /**
     * 分类分页
     *
     * @param parseInt 解析int
     * @param pageSize 页面大小
     * @return {@link Page}<{@link CourseCategory}>
     */
    @Override
    public Page<CourseCategory> categoryPage(int parseInt, int pageSize) {
        return page(BeanFactory.getInstance().getCourseCategory(), parseInt, pageSize);
    }

    /**
     * 按id选择一个
     *
     * @param courseCategory 课程类别
     * @return {@link CourseCategory}
     */
    @Override
    public CourseCategory selectOneById(CourseCategory courseCategory) {
        return super.selectOneById(courseCategory);
    }
}
