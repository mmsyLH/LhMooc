package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.CommentCourseDAO;
import asia.lhweb.lhmooc.model.bean.CommentCourse;

import java.util.List;

/**
 * 评论视频DAO
 *
 * @author Administrator
 * @description 针对表【comment_course(视频评论表)】的数据库操作DAO
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public class CommentCourseDAOImpl extends BasicDAO<CommentCourse>  implements CommentCourseDAO {
    /**
     * 选择所有
     *
     * @return {@link List}<{@link CommentCourse}>
     */
    @Override
    public List<CommentCourse> selectAll() {
        return selectAll(new CommentCourse());
    }
}
