package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.CourseChapterDAO;
import asia.lhweb.lhmooc.model.bean.CourseChapter;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class CourseChapterDAOImpl extends BasicDAO<CourseChapter> implements CourseChapterDAO {
    /**
     * 按id选择一个
     *
     * @param courseChapter 章课程
     * @return {@link CourseChapter}
     */
    @Override
    public CourseChapter selectOneById(CourseChapter courseChapter) {
        return super.selectOneById(courseChapter);
    }
}
