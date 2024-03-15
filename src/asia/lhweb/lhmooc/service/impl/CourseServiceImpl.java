package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.CommentVideoDAO;
import asia.lhweb.lhmooc.dao.CourseDAO;
import asia.lhweb.lhmooc.dao.FollowVideoDAO;
import asia.lhweb.lhmooc.dao.LikeVideoDAO;
import asia.lhweb.lhmooc.dao.impl.CommentVideoDAOImpl;
import asia.lhweb.lhmooc.dao.impl.CourseDAOImpl;
import asia.lhweb.lhmooc.dao.impl.FollowVideoDAOImpl;
import asia.lhweb.lhmooc.dao.impl.LikeVideoDAOImpl;
import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.model.bean.CommentVideo;
import asia.lhweb.lhmooc.model.bean.Course;
import asia.lhweb.lhmooc.model.bean.FollowVideo;
import asia.lhweb.lhmooc.model.bean.LikeVideo;
import asia.lhweb.lhmooc.service.CourseService;

import java.util.List;

/**
 * 课程服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO = new CourseDAOImpl();
    private CommentVideoDAO commentVideoDAO = new CommentVideoDAOImpl();
    private LikeVideoDAO likeVideoDAO = new LikeVideoDAOImpl();
    private FollowVideoDAO followVideoDAO = new FollowVideoDAOImpl();

    public CourseServiceImpl() {

    }

    /**
     * 获得排名top8课程
     *
     * @param sort 排序
     * @return {@link List}<{@link Course}>
     */
    @Override
    public List<Course> getSortCoursesTop8(String sort) {
        // todo 根据传入的sort去判断 根据什么分类
        List<Course> courseList = courseDAO.selectAll(BeanFactory.getInstance().getCourse());// 课程表
        List<CommentVideo> commentVideoList = commentVideoDAO.selectAll();// 课程评论表
        List<LikeVideo> likeVideoList = likeVideoDAO.selectAll();// 课程点赞表
        List<FollowVideo> followVideoList = followVideoDAO.selectAll();// 课程收藏表
        // 根据排序方式进行排序
        // todo 学习一下steam流 整合新的list??? 然后再排序？还是从数据库层面就开始多表联查？

        // 对所有课程进行排序
        // courses.sort((player1, player2) -> {
        //     // 降序排序
        //     return player2.getPlayerScore() - player1.getPlayerScore();
        // });
        //
        // // 获取前三名玩家
        // int size = Math.min(3, allUser.size()); // 如果玩家数量不足三人，只返回实际数量的玩家
        // return allUser.subList(0, size);


        return null;
    }

    /**
     * 按类别获取
     *
     * @param course 课程
     * @return {@link List}<{@link Course}>
     */
    @Override
    public List<Course> getByCategory(Course course) {
        return courseDAO.selectAll(course);
    }

    /**
     * 添加
     *
     * @param course 课程
     * @return boolean
     */
    @Override
    public boolean add(Course course) {
        return courseDAO.save(course)!=-1;
    }
}
