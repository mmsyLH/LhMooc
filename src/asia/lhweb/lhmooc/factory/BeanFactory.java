package asia.lhweb.lhmooc.factory;

import asia.lhweb.lhmooc.model.bean.*;

/**
 * bean工厂 仅仅是用来提供一个类型拿去使用basticDao的工程
 *
 * @author 罗汉
 * @date 2024/03/13
 */
public class BeanFactory {
    private static final BeanFactory instance = new BeanFactory();
    private static final CommentVideo commentVideo = new CommentVideo();
    private static final Course course = new Course();
    private static final CourseCategory courseCategory = new CourseCategory();
    private static final CourseChapter courseChapter = new CourseChapter();
    private static final CourseVideo courseVideo = new CourseVideo();
    private static final FollowVideo followVideo = new FollowVideo();
    private static final LikeVideo likeVideo = new LikeVideo();
    private static final MoocAdmin moocAdmin = new MoocAdmin();
    private static final MoocUser moocUser = new MoocUser();
    private static final Orders orders = new Orders();
    private static final PlayHistory playHistory = new PlayHistory();
    private static final Transaction transaction = new Transaction();

    private BeanFactory() {
        // 私有构造函数，防止外部实例化
    }

    /**
     * 获取 BeanFactory 的实例
     *
     * @return BeanFactory 实例
     */
    public static BeanFactory getInstance() {
        return instance;
    }

    /**
     * 获取 CommentVideo 实例
     *
     * @return CommentVideo 实例
     */
    public CommentVideo getCommentVideo() {
        return commentVideo;
    }

    /**
     * 获取 Course 实例
     *
     * @return Course 实例
     */
    public Course getCourse() {
        return course;
    }

    /**
     * 获取 CourseCategory 实例
     *
     * @return CourseCategory 实例
     */
    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    /**
     * 获取 CourseChapter 实例
     *
     * @return CourseChapter 实例
     */
    public CourseChapter getCourseChapter() {
        return courseChapter;
    }

    /**
     * 获取 CourseVideo 实例
     *
     * @return CourseVideo 实例
     */
    public CourseVideo getCourseVideo() {
        return courseVideo;
    }

    /**
     * 获取 FollowVideo 实例
     *
     * @return FollowVideo 实例
     */
    public FollowVideo getFollowVideo() {
        return followVideo;
    }

    /**
     * 获取 LikeVideo 实例
     *
     * @return LikeVideo 实例
     */
    public LikeVideo getLikeVideo() {
        return likeVideo;
    }

    /**
     * 获取 MoocAdmin 实例
     *
     * @return MoocAdmin 实例
     */
    public MoocAdmin getMoocAdmin() {
        return moocAdmin;
    }

    /**
     * 获取 MoocUser 实例
     *
     * @return MoocUser 实例
     */
    public MoocUser getMoocUser() {
        return moocUser;
    }

    /**
     * 获取 Orders 实例
     *
     * @return Orders 实例
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * 获取 PlayHistory 实例
     *
     * @return PlayHistory 实例
     */
    public PlayHistory getPlayHistory() {
        return playHistory;
    }

    /**
     * 获取 Transaction 实例
     *
     * @return Transaction 实例
     */
    public Transaction getTransaction() {
        return transaction;
    }
}
