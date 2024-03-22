package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.CommentCourseDAO;
import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CommentCourse;
import asia.lhweb.lhmooc.service.CommentCourseService;

/**
 * @author :罗汉
 * @date : 2024/3/13
 */
public class CommentCourseServiceImpl implements CommentCourseService {
    //课程评论dao
    private CommentCourseDAO commentCourseDAO= BeanFactory.getInstance().getCommentCourseDAOImpl();
    @Override
    public Page<CommentCourse> page(CommentCourse commentCourse, int pageNo, int pageSize) {
        return commentCourseDAO.page(commentCourse,pageNo,pageSize);
    }

    /**
     * 真正删除
     *
     * @param commentCourse 评论课程
     * @return boolean
     */
    @Override
    public boolean realDelete(CommentCourse commentCourse) {
        return commentCourseDAO.realDelete(commentCourse)!=-1;
    }
}
