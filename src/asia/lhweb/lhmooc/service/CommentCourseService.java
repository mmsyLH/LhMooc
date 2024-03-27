package asia.lhweb.lhmooc.service;


import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CommentCourse;

/**
* @author Administrator
* @description 针对表【comment_course(课程评论表)】的数据库操作Service
* @createDate 2024-03-13 19:57:04
*/
public interface CommentCourseService {

    Page<CommentCourse> page(CommentCourse commentCourse, int parseInt, int parseInt1);

    /**
     * 真正删除
     *
     * @param commentCourse 评论课程
     * @return boolean
     */
    boolean realDelete(CommentCourse commentCourse);

    /**
     * 添加评论
     *
     * @param parseInt  解析int
     * @param parseInt1 解析int1
     * @param content   内容
     * @return {@link Result}
     */
    Result commentAdd(int parseInt, int parseInt1, String content);
}
