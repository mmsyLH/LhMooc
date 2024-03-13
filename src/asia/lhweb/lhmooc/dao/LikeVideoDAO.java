package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.LikeVideo;

import java.util.List;

/**
 * 比如视频服务
 *
 * @author Administrator
 * @description 针对表【like_video(点赞表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface LikeVideoDAO {

    /**
     * 选择所有
     *
     * @return {@link List}<{@link LikeVideo}>
     */
    List<LikeVideo> selectAll();
}
