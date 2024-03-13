package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.CommentVideo;

import java.util.List;

/**
 * 评论视频DAO
 *
 * @author Administrator
 * @description 针对表【comment_video(视频评论表)】的数据库操作DAO
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CommentVideoDAO{

    /**
     * 选择所有
     *
     * @return {@link List}<{@link CommentVideo}>
     */
    List<CommentVideo> selectAll();
}
