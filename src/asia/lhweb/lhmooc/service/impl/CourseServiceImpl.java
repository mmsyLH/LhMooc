package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.*;
import asia.lhweb.lhmooc.dao.impl.*;
import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.*;
import asia.lhweb.lhmooc.model.vo.CommentCourseVo;
import asia.lhweb.lhmooc.model.vo.CourseChapterVo;
import asia.lhweb.lhmooc.model.vo.CourseVo;
import asia.lhweb.lhmooc.service.CourseService;
import asia.lhweb.lhmooc.utils.DataUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO = new CourseDAOImpl();
    private CourseCategoryDAO courseCategoryDAO = new CourseCategoryDAOImpl();
    // 用户dao
    private MoocUserDAO moocUserDAO = new MoocUserDAOImpl();
    private CourseVideoDAO courseVideoDAO = new CourseVideoDAOImpl();
    private CourseChapterDAO courseChapterDAO = new CourseChapterDAOImpl();
    private CommentCourseDAO commentCourseDAO = new CommentCourseDAOImpl();
    private LikeCourseDAO likeCourseDAO = new LikeCourseDAOImpl();
    private FollowCourseDAO followCourseDAO = new FollowCourseDAOImpl();
    // 购买历史记录dao
    private BuycourseHistoryDAO buycourseHistoryDAO = new BuycourseHistoryDAOImpl();
    // 订单dao
    private OrdersDAO ordersDAO = new OrdersDAOImpl();
    // 流水表dao
    private TransactionDAO transactionDAO =new TransactionDAOImpl();

    public CourseServiceImpl() {

    }

    /**
     * 获得排名top8课程
     *
     * @param sortType 排序类型，"0"表示默认不排序，"1"表示按收藏量排序，"2"表示按点赞量排序，"3"表示按评论量排序
     * @return {@link List}<{@link CourseVo}>
     */
    public List<CourseVo> getSortCoursesTop8(String sortType) {
        List<Course> courseList = courseDAO.selectAll(BeanFactory.getInstance().getCourse());

        // 构建 CourseVo 对象列表
        ArrayList<CourseVo> courseVoList = new ArrayList<>();
        for (Course course : courseList) {
            // 构建 CourseVo 对象
            CourseVo courseVo = new CourseVo();
            courseVo.setCourseid(course.getCourseid());
            courseVo.setCoursename(course.getCoursename());
            courseVo.setProfile(course.getProfile());
            courseVo.setPrice(course.getPrice());
            courseVo.setImgurl(course.getImgurl());
            courseVo.setCategoryid(course.getCategoryid());
            courseVo.setIsdelete(course.getIsdelete());
            courseVo.setCreatetime(course.getCreatetime());

            // 获取课程的评论量、点赞量和收藏量
            int commentCount = getCommentCount(course);
            int likeCount = getLikeCount(course);
            int followCount = getFollowCount(course);

            // 设置课程的评论量、点赞量和收藏量
            courseVo.setCommentCount(commentCount);
            courseVo.setLikeCount(likeCount);
            courseVo.setFollowCount(followCount);

            courseVoList.add(courseVo);
        }

        // 根据排序类型对课程Vo列表进行排序
        switch (sortType) {
            case "1":
                courseVoList.sort(Comparator.comparingInt(CourseVo::getFollowCount).reversed());
                break;
            case "2":
                courseVoList.sort(Comparator.comparingInt(CourseVo::getLikeCount).reversed());
                break;
            case "3":
                courseVoList.sort(Comparator.comparingInt(CourseVo::getCommentCount).reversed());
                break;
            default:
                break;
        }

        // 返回前8个课程Vo对象
        return courseVoList.stream().limit(Math.min(courseVoList.size(), 8)).collect(Collectors.toList());
    }

    /**
     * 获取课程的评论量
     *
     * @param course 课程
     * @return int
     */
    private int getCommentCount(Course course) {
        CommentCourse commentCourse = new CommentCourse();
        commentCourse.setCourseid(course.getCourseid());
        return commentCourseDAO.getTotalRowAnd(commentCourse);
    }

    /**
     * 获取课程的点赞量
     *
     * @param course 课程
     * @return int
     */
    private int getLikeCount(Course course) {
        LikeCourse likeCourse = new LikeCourse();
        likeCourse.setCourseid(course.getCourseid());
        return likeCourseDAO.getTotalRowAnd(likeCourse);
    }

    /**
     * 获得关注数
     *
     * @param course 课程
     * @return int
     */
    private int getFollowCount(Course course) {
        FollowCourse followCourse = new FollowCourse();
        followCourse.setCourseid(course.getCourseid());
        return followCourseDAO.getTotalRowAnd(followCourse);
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
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setCategoryid(course.getCategoryid());
        CourseCategory find = courseCategoryDAO.selectOneById(courseCategory);
        if (find == null) {
            return false;
        }
        return courseDAO.save(course) != -1;
    }

    /**
     * 页面和分类
     *
     * @param pageNo           页面编号
     * @param pageSize         页面大小
     * @param sortType         排序类型，"0"表示默认不排序，"1"表示按收藏量排序，"2"表示按点赞量排序，"3"表示按评论量排序
     * @param courseCategoryId 课程类别编号
     * @return {@link Page}<{@link CourseVo}>
     */
    @Override
    public Page<CourseVo> pageAndByCategory(int courseCategoryId, int pageNo, int pageSize, String sortType) {
        // 查询指定分类下的所有课程
        Course courseTest = new Course();
        courseTest.setCategoryid(courseCategoryId);
        Page<Course> coursePage = courseDAO.pageByAnd(courseTest, pageNo, pageSize);

        // 构建 CourseVo 的分页模型
        Page<CourseVo> courseVoPage = new Page<>();
        courseVoPage.setPageNo(pageNo);
        courseVoPage.setPageTotalCount(coursePage.getPageTotalCount());
        courseVoPage.setTotalRow(coursePage.getTotalRow());
        courseVoPage.setPageSize(pageSize);
        List<CourseVo> courseVoList = new ArrayList<>();
        CourseVo courseVo;
        for (Course course : coursePage.getItems()) {
            // 查询收藏量
            int followCount = getFollowCount(course);

            // 查询点赞量
            int likeCount = getLikeCount(course);

            // 查询评论量
            int commentCount = getCommentCount(course);

            // 构建 CourseVo 对象
            courseVo = new CourseVo();
            DataUtils.copyNonNullProperties(course, courseVo);

            courseVo.setFollowCount(followCount);
            courseVo.setLikeCount(likeCount);
            courseVo.setCommentCount(commentCount);

            courseVoList.add(courseVo);


        }
        courseVoPage.setItems(courseVoList);

        // 根据排序类型进行排序
        switch (sortType) {// 1收藏 2点赞 3评论
            case "1":  // 按收藏量排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted((o1, o2) -> o2.getFollowCount() - o1.getFollowCount())
                        // .limit(pageSize)
                        .collect(Collectors.toList()));
                break;
            case "2":  // 按点赞量排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted((o1, o2) -> o2.getLikeCount() - o1.getLikeCount())
                        // .limit(pageSize)
                        .collect(Collectors.toList()));
                break;
            case "3":  // 按评论量排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted((o1, o2) -> o2.getCommentCount() - o1.getCommentCount())
                        // .limit(pageSize)
                        .collect(Collectors.toList()));
                break;
            case "4":  // 按时间排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted((o1, o2) -> Long.compare(o2.getCreatetime().getTime(), o1.getCreatetime().getTime())).collect(Collectors.toList()));
                break;
        }
        return courseVoPage;
    }

    /**
     * 获取课程细节
     * 获取指定课程的详细信息
     *
     * @param courseId 课程的ID标识
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @return 返回一个结果对象，其中包含课程的详细信息。如果课程不存在，则返回错误信息。
     */
    @Override
    public Result<CourseVo> getCourseDetail(int courseId, int pageNo, int pageSize) {
        // 初始化课程对象并设置课程ID
        Course course = new Course();
        course.setCourseid(courseId);

        // 通过课程ID查询课程信息
        Course findCourse = courseDAO.selectOneById(course);

        // 如果查询结果为空，则返回课程不存在的错误信息
        if (findCourse == null) return Result.error("您要查询的课程不存在");

        // 创建课程详情视图对象并填充数据
        CourseVo courseVo = new CourseVo();
        DataUtils.copyNonNullProperties(findCourse, courseVo);

        // 查询课程章节信息
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setCouseid(courseVo.getCourseid());
        List<CourseChapter> courseChapterList = courseChapterDAO.selectAll(courseChapter);

        // 遍历章节信息，转换为视图对象
        List<CourseChapterVo> courseChapterVoList = new ArrayList<>();
        CourseChapterVo courseChapterTemp;
        for (CourseChapter chapter : courseChapterList) {
            courseChapterTemp = new CourseChapterVo();
            DataUtils.copyNonNullProperties(chapter, courseChapterTemp);
            courseChapterVoList.add(courseChapterTemp);
        }

        // 为每个章节获取其下的所有视频
        for (CourseChapterVo courseChapterVo : courseChapterVoList) {
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setChapterid(courseChapterVo.getChapterid());
            List<CourseVideo> courseVideoList = courseVideoDAO.selectAll(courseVideo);
            courseChapterVo.setCourseVideoList(courseVideoList);
        }

        // 设置课程的章节列表
        courseVo.setCourseChapterList(courseChapterVoList);

        // 查询课程评论信息
        CommentCourse commentCourse = new CommentCourse();
        commentCourse.setCourseid(courseVo.getCourseid());
        List<CommentCourse> commentCourseList = commentCourseDAO.selectAll(commentCourse);

        // 将评论信息转换为视图对象，并补充用户名和头像URL
        List<CommentCourseVo> commentCourseVoList = new ArrayList<>();
        CommentCourseVo commentCourseVoTemp;
        MoocUser moocUserTemp;
        for (CommentCourse commentCourseTemp : commentCourseList) {
            commentCourseVoTemp = new CommentCourseVo();
            DataUtils.copyNonNullProperties(commentCourseTemp, commentCourseVoTemp);
            moocUserTemp = new MoocUser();
            moocUserTemp.setId(commentCourseTemp.getUserid());
            moocUserTemp = moocUserDAO.selectOneById(moocUserTemp);
            if (moocUserTemp != null) {
                commentCourseVoTemp.setImgurl(moocUserTemp.getAvatar());
                commentCourseVoTemp.setNickname(moocUserTemp.getNickname());
            }
            commentCourseVoList.add(commentCourseVoTemp);
        }
        // 按照的时间排序 最晚评论的排在越上面
        commentCourseVoList.sort((o1, o2) -> Long.compare(o2.getCreatetime().getTime(), o1.getCreatetime().getTime()));
        Page<CommentCourseVo> page = new Page<>();
        page = page.getPageByList(pageNo, pageSize, commentCourseVoList);
        courseVo.setCommentCourseVoPage(page);

        // 查询课程的点赞数
        LikeCourse likeCourse = new LikeCourse();
        likeCourse.setCourseid(courseVo.getCourseid());
        int likeCount = likeCourseDAO.getTotalRowAnd(likeCourse);
        courseVo.setLikeCount(likeCount);

        // 查询课程的收藏数
        FollowCourse followCourse = new FollowCourse();
        followCourse.setCourseid(courseVo.getCourseid());
        int followCount = followCourseDAO.getTotalRowAnd(followCourse);
        courseVo.setFollowCount(followCount);

        // 返回包含课程详细信息的成功结果
        return Result.success(courseVo, "获取指定课程的详细信息成功");
    }


    /**
     * 真正删除
     *
     * @param course 课程
     * @return boolean
     */
    @Override
    public boolean realDelete(Course course) {
        return courseDAO.realDelete(course) != -1;
    }

    /**
     * 更新
     *
     * @param courseId   进程id
     * @param coursename coursename
     * @param profile    配置文件
     * @param price      价格
     * @return {@link Result}
     */
    @Override
    public Result update(int courseId, String coursename, String profile, String price) {
        Course course = new Course();
        course.setCourseid(courseId);
        Course findCourse = courseDAO.selectOneById(course);
        if (findCourse == null) return Result.error("您要更新的课程不存在");
        findCourse.setCoursename(coursename);
        findCourse.setProfile(profile);
        findCourse.setPrice(Double.parseDouble(price));
        if (courseDAO.update(findCourse) != -1) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    /**
     * 购买课程
     *
     * @param userId   用户id
     * @param courseId 进程id
     * @return {@link Result}
     */
    @Override
    public Result buyCourse(int userId, int courseId) {
        // 获取课程的金额
        Course course = new Course();
        course.setCourseid(courseId);

        Course findCourse = courseDAO.selectOneById(course);
        if (findCourse == null) return Result.error("您要购买的课程不存在");

        // 查询用户的金额
        MoocUser moocUser = new MoocUser();
        moocUser.setId(userId);
        moocUser = moocUserDAO.selectOneById(moocUser);
        if (moocUser == null) return Result.error("用户不存在");

        if (moocUser.getWallet() < findCourse.getPrice()) {
            return Result.error("余额不足");
        }
        moocUser.setWallet(moocUser.getWallet() - findCourse.getPrice());
        if (moocUserDAO.update(moocUser) != -1) {
            // 添加到购买记录中
            BuycourseHistory buycourseHistory = new BuycourseHistory();
            buycourseHistory.setCourseid(courseId);
            buycourseHistory.setUserid(userId);
            buycourseHistory.setPaymentmethod("余额支付");
            buycourseHistory.setMoney(findCourse.getPrice());
            if (buycourseHistoryDAO.save(buycourseHistory) <= 0) {
                return Result.error("购买失败(购买记录)");
            }
            // 流水记录
            Transaction transaction = new Transaction();
            transaction.setTransactionid(moocUser.getUsername() + DataUtils.getCode());
            transaction.setUserid(userId);
            transaction.setTransactionstatus(1);// 1 成功 2失败 3进行中
            transaction.setAmount(findCourse.getPrice());
            transaction.setTransactiontype(2);// 1 充值 2消费
            transaction.setInfo("用户消费金额购买课程");
            if (transactionDAO.save(transaction) <=0) {
                return Result.error("购买失败(流水表)");
            }

            // 订单记录
            Orders orders = new Orders();
            orders.setUserid(userId);
            orders.setOrderid(DataUtils.getCode());
            orders.setCourseid(courseId);
            orders.setPaychannel(3);
            orders.setOrderamount(findCourse.getPrice());
            orders.setOrderstatus(1);// 已付款
            if (ordersDAO.save(orders) <= 0) {
                return Result.error("购买失败(订单表)");
            }
            return Result.success("购买成功");
        }
        return Result.success("购买成功");
    }

}
