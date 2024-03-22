package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CommentCourse;

import java.util.List;

/**
 * 评论视频DAO
 *
 * @author Administrator
 * @description 针对表【comment_Course(视频评论表)】的数据库操作DAO
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CommentCourseDAO{

    int getTotalRowAnd(CommentCourse commentCourse);

    /**
     * 选择所有
     *
     * @param commentCourse 评论课程
     * @return {@link List}<{@link CommentCourse}>
     */
    List<CommentCourse> selectAll(CommentCourse commentCourse);

    Page<CommentCourse> page(CommentCourse commentCourse, int pageNo, int pageSize);

    int realDelete(CommentCourse commentCourse);
}
