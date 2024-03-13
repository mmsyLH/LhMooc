package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.FollowVideoDAO;
import asia.lhweb.lhmooc.model.bean.FollowVideo;

import java.util.List;

/**
 * 收藏视频
 *
 * @author 罗汉
 * @date 2024/03/13
 */
public class FollowVideoDAOImpl extends BasicDAO<FollowVideo> implements FollowVideoDAO {
    /**
     * 选择所有
     *
     * @return {@link List}<{@link FollowVideo}>
     */
    @Override
    public List<FollowVideo> selectAll() {
        return selectAll(new FollowVideo());
    }
}
