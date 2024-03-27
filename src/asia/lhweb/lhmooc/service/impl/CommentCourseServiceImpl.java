package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
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

    /**
     * 添加评论
     *
     * @param content  内容
     * @param userId   用户id
     * @param courseId 进程id
     * @return {@link Result}
     */
    @Override
    public Result commentAdd(int userId, int courseId, String content) {
        CommentCourse commentCourse = new CommentCourse();
        commentCourse.setUserid(userId);
        commentCourse.setCourseid(courseId);
        commentCourse.setContent(content);
        if (commentCourseDAO.save(commentCourse) > 0) {
            return Result.success("添加评论成功！");
        }else {
            return Result.error("添加评论失败！");
        }
    }
}
