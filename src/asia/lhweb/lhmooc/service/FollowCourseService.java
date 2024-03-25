package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.FollowCourse;

/**
* @author Administrator
* @description 针对表【follow_course(课程收藏表)】的数据库操作Service
* @createDate 2024-03-13 19:57:04
*/
public interface FollowCourseService {

    /**
     * 通过id获取关注页面
     * 通过id获取关注页面 查询后降序分页展示
     *
     * @param userId   用户id
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @return {@link Result}<{@link Page}<{@link FollowCourse}>>
     */
    Result<Page<FollowCourse>> getFollowPageById(int userId, int pageNo, int pageSize);

    Page<FollowCourse> page(FollowCourse followCourse, int parseInt, int parseInt1);

    /**
     * 真正删除
     *
     * @param followCourse 遵循课程
     * @return boolean
     */
    boolean realDelete(FollowCourse followCourse);

    /**
     * 遵循添加
     *
     * @param parseInt  解析int
     * @param parseInt1 解析int1
     * @return {@link Result}
     */
    Result followAdd(int parseInt, int parseInt1);
}
