package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.*;
import asia.lhweb.lhmooc.dao.impl.*;
import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.*;
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

    private CourseVideoDAO courseVideoDAO = new CourseVideoDAOImpl();
    private CourseChapterDAO courseChapterDAO = new CourseChapterDAOImpl();
    private CommentCourseDAO commentCourseDAO = new CommentCourseDAOImpl();
    private LikeCourseDAO likeCourseDAO = new LikeCourseDAOImpl();
    private FollowCourseDAO followCourseDAO = new FollowCourseDAOImpl();

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
     * 获取指定课程的详细信息
     *
     * @param courseId 课程的ID标识
     * @return 返回一个结果对象，其中包含课程的详细信息。如果课程不存在，则返回错误信息。
     */
    @Override
    public Result<CourseVo> getCourseDetail(int courseId) {
        Course course = new Course();
        course.setCourseid(courseId);

        // 通过课程ID查询课程信息
        Course findCourse = courseDAO.selectOneById(course);

        // 如果查询结果为空，则返回课程不存在的错误信息
        if (findCourse == null) return Result.error("您要查询的课程不存在");

        // 创建课程详情视图对象并填充数据
        CourseVo courseVo = new CourseVo();
        DataUtils.copyNonNullProperties(findCourse, courseVo);

        // 根据课程ID查询课程章节信息
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setCouseid(courseVo.getCourseid());
        List<CourseChapter> courseChapterList = courseChapterDAO.selectAll(courseChapter);

        List<CourseChapterVo> courseChapterVoList = new ArrayList<>();
        CourseChapterVo courseChapterTemp;
        for (CourseChapter chapter : courseChapterList) {
            courseChapterTemp= new CourseChapterVo();
            DataUtils.copyNonNullProperties(chapter,courseChapterTemp);
            courseChapterVoList.add(courseChapterTemp);
        }

        CourseVideo courseVideo;
        List<CourseVideo> courseVideoList;
        for (CourseChapterVo courseChapterVo : courseChapterVoList) {
            courseVideo = new CourseVideo();
            courseVideo.setChapterid(courseChapterVo.getChapterid());
            // 根据课程章节去获取章节下的全部视频
            courseVideoList = courseVideoDAO.selectAll(courseVideo);
            courseChapterVo.setCourseVideoList(courseVideoList);

        }
        courseVo.setCourseChapterList(courseChapterVoList);
        // 根据课程ID查询该课程的评论信息
        CommentCourse commentCourse = new CommentCourse();
        commentCourse.setCourseid(courseVo.getCourseid());
        List<CommentCourse> commentCourseList = commentCourseDAO.selectAll(commentCourse);

        courseVo.setCommentList(commentCourseList);

        return Result.success(courseVo,"获取指定课程的详细信息成功");
    }

}
