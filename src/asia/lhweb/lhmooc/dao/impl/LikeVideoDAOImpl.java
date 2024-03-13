package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.LikeVideoDAO;
import asia.lhweb.lhmooc.model.bean.LikeVideo;

import java.util.List;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class LikeVideoDAOImpl extends BasicDAO<LikeVideo> implements LikeVideoDAO {
    /**
     * 选择所有
     *
     * @return {@link List}<{@link LikeVideo}>
     */
    @Override
    public List<LikeVideo> selectAll() {
        return selectAll(new LikeVideo());
    }
}
