package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.LikeCourseDAO;
import asia.lhweb.lhmooc.model.bean.LikeCourse;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class LikeCourseDAOImpl extends BasicDAO<LikeCourse> implements LikeCourseDAO {

    /**
     * 按id选择一个
     *
     * @param likeCourse 喜欢课程
     * @return {@link LikeCourse}
     */
    @Override
    public LikeCourse selectOneById(LikeCourse likeCourse) {
        return super.selectOneById(likeCourse);
    }
}
