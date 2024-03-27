package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.CourseCategoryDAO;
import asia.lhweb.lhmooc.dao.impl.CourseCategoryDAOImpl;
import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseCategory;
import asia.lhweb.lhmooc.service.CourseCategoryService;

import java.util.List;

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
     * @param courseCategory
     * @param parseInt       解析int
     * @param pageSize       页面大小
     * @return {@link Page}<{@link CourseCategory}>
     */
    @Override
    public Page<CourseCategory> page(CourseCategory courseCategory, int parseInt, int pageSize) {
        return courseDAO.page(courseCategory,parseInt, pageSize);
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

    /**
     * 真正执行删除操作的函数。
     * 本函数用于删除指定的课程类别。注意，此处的删除操作是真正的物理删除，而不是逻辑删除。
     *
     * @param category 需要被删除的课程类别对象。
     * @return boolean 返回操作是否成功。
     */
    @Override
    public boolean realDelete(CourseCategory category) {
        return  courseDAO.realDelete(category)!=-1;
    }

    /**
     * 添加一个课程类别到集合中。
     *
     * @param category 想要添加的课程类别对象。
     * @return boolean 如果添加成功返回true，否则返回false。
     */
    @Override
    public boolean add(CourseCategory category) {

        return courseDAO.save(category)!=-1;
    }

    /**
     * 得到所有课程分类
     *
     * @return {@link List}<{@link CourseCategory}>
     */
    @Override
    public List<CourseCategory> getAll() {
        return courseDAO.selectAll(BeanFactory.getInstance().getCourseCategory());
        // return courseDAO.selectAll(new CourseCategory());
    }


}
