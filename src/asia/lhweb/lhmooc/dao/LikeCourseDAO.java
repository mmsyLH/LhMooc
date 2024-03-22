package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.LikeCourse;

import java.util.List;

/**
 * 比如视频服务
 *
 * @author Administrator
 * @description 针对表【like_Course(点赞表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface LikeCourseDAO {


    /**
     * 得到总行数和
     *
     * @param likeCourse 喜欢课程
     * @return int
     */
    int getTotalRowAnd(LikeCourse likeCourse);

    /**
     * 选择所有
     *
     * @param likeCourse 喜欢课程
     * @return {@link List}<{@link LikeCourse}>
     */
    List<LikeCourse> selectAll(LikeCourse likeCourse);

    /**
     * 页面
     *
     * @param likeCourse 喜欢课程
     * @param pageNo     页面没有
     * @param pageSize   页面大小
     * @return {@link Page}<{@link LikeCourse}>
     */
    Page<LikeCourse> page(LikeCourse likeCourse, int pageNo, int pageSize);

    /**
     * 真正删除
     *
     * @param likeCourse 喜欢课程
     * @return int
     */
    int realDelete(LikeCourse likeCourse);
}
