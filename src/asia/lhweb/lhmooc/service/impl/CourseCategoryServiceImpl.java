package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.CourseCategoryDAO;
import asia.lhweb.lhmooc.dao.impl.CourseCategoryDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseCategory;
import asia.lhweb.lhmooc.service.CourseCategoryService;

/**
 * 课程类别服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class CourseCategoryServiceImpl implements CourseCategoryService {
    private CourseCategoryDAO courseDAO=new CourseCategoryDAOImpl();
    /**
     * 页面
     *
     * @param parseInt 解析int
     * @param pageSize 页面大小
     * @return {@link Page}<{@link CourseCategory}>
     */
    @Override
    public Page<CourseCategory> page(int parseInt, int pageSize) {
        return courseDAO.categoryPage(parseInt, pageSize);
    }

    /**
     * 更新
     *
     * @param category 类别
     * @return boolean
     */
    @Override
    public boolean update(CourseCategory category) {
        return courseDAO.update(category)!=-1;
    }

    /**
     * 逻辑删除
     *
     * @param category 类别
     * @return boolean
     */
    @Override
    public boolean delete(CourseCategory category) {
        return courseDAO.delete(category)!=-1;
    }
}
