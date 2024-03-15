package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.*;
import asia.lhweb.lhmooc.dao.impl.*;
import asia.lhweb.lhmooc.factory.BeanFactory;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.*;
import asia.lhweb.lhmooc.model.vo.CourseVo;
import asia.lhweb.lhmooc.service.CourseService;

import java.util.ArrayList;
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
    private CommentCourseDAO commentCourseDAO = new CommentCourseDAOImpl();
    private LikeCourseDAO likeCourseDAO = new LikeCourseDAOImpl();
    private FollowCourseDAO followCourseDAO = new FollowCourseDAOImpl();

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
        List<CommentCourse> commentCourseList = commentCourseDAO.selectAll();// 课程评论表
        List<LikeCourse> likeCourseList = likeCourseDAO.selectAll();// 课程点赞表
        List<FollowCourse> followCourseList = followCourseDAO.selectAll();// 课程收藏表
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
        for (Course course : coursePage.getItems()) {
            // 查询收藏量
            FollowCourse followCourse = new FollowCourse();
            followCourse.setCourseid(course.getCourseid());
            int followCount = followCourseDAO.getTotalRowAnd(followCourse);

            // 查询点赞量
            LikeCourse likeCourse = new LikeCourse();
            likeCourse.setCourseid(course.getCourseid());
            int likeCount = likeCourseDAO.getTotalRowAnd(likeCourse);

            // 查询评论量
            CommentCourse commentCourse = new CommentCourse();
            commentCourse.setCourseid(course.getCourseid());
            int commentCount = commentCourseDAO.getTotalRowAnd(commentCourse);

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
            courseVo.setFollowCount(followCount);
            courseVo.setLikeCount(likeCount);
            courseVo.setCommentCount(commentCount);
            courseVoList.add(courseVo);


        }
        courseVoPage.setItems(courseVoList);

        // 根据排序类型进行排序
        switch (sortType) {// 1收藏 2点赞 3评论
            case "1":  // 按收藏量排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted
                                ((o1, o2) -> o2.getFollowCount() - o1.getFollowCount())
                        // .limit(pageSize)
                        .collect(Collectors.toList()));
                break;
            case "2":  // 按点赞量排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted
                                ((o1, o2) -> o2.getLikeCount() - o1.getLikeCount())
                        // .limit(pageSize)
                        .collect(Collectors.toList()));
                break;
            case "3":  // 按评论量排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted
                                ((o1, o2) -> o2.getCommentCount() - o1.getCommentCount())
                        // .limit(pageSize)
                        .collect(Collectors.toList()));
                break;
            case "4":  // 按时间排序
                courseVoPage.setItems(courseVoPage.getItems().stream().sorted
                                ((o1, o2) -> Long.compare(o2.getCreatetime().getTime(), o1.getCreatetime().getTime()))
                        .collect(Collectors.toList()));
                break;

        }
        return courseVoPage;
    }

}
