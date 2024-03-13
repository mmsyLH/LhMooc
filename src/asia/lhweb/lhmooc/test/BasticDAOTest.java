package asia.lhweb.lhmooc.test;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class BasticDAOTest {
    public static void main(String[] args) {
        // 1 查询全部语句 ok
        // CommentVideoDAO courseCategoryDAO = new CommentVideoDAOImpl();
        // courseCategoryDAO.selectAll();
        //
        // CommentVideoDAOImpl courseCategoryDAO2 = new CommentVideoDAOImpl();
        //
        // List<CommentVideo> commentVideoList = courseCategoryDAO2.selectAll(new CommentVideo());
        // System.out.println(commentVideoList);
        // courseCategoryDAO2.page(new CommentVideo(),1,1);


        // TestDao t = new Test();
        // t.myTest();


        // 2 更新语句 ok
        // CourseCategory courseCategory = new CourseCategory();
        // courseCategory.setCategoryid(2);
        // courseCategory.setCategoryname("123");
        // System.out.println(courseCategory);
        // CourseCategoryDAOImpl courseCategoryDAO1 = new CourseCategoryDAOImpl();
        // courseCategoryDAO1.update(courseCategory);


        // 3 删除语句 根据id删除ok
        // CourseCategory courseCategory = new CourseCategory();
        // courseCategory.setCategoryid(2);
        // courseCategory.setCategoryname("123");
        // System.out.println(courseCategory);
        // CourseCategoryDAOImpl courseCategoryDAO1 = new CourseCategoryDAOImpl();
        // int i = courseCategoryDAO1.deleteOne(courseCategory);

        // 4 逻辑删除 ok
        // CourseCategory courseCategory = new CourseCategory();
        // courseCategory.setCategoryid(7);
        // CourseCategoryDAOImpl courseCategoryDAO1 = new CourseCategoryDAOImpl();
        // int i = courseCategoryDAO1.delete(courseCategory);
        // if (i > 0) {
        //     System.out.println("删除成功");
        // }

        // 5 添加语句

        // 6 通过主键去查询单条记录 ok
        // Course course = new Course();
        // course.setCourseid(1);
        //
        // CourseDAOImpl courseDAO = new CourseDAOImpl();
        // Course course1 = courseDAO.selectOneById(course);
        // System.out.println(course1);

        // 7 测试动态sql语句查询所有
        // Course course = new Course();
        // // course.setCourseid(1);
        // course.setPrice(143);
        // course.setImgurl("img1.jpg");
        //
        // CourseDAOImpl courseDAO = new CourseDAOImpl();
        // List<Course> courseList = courseDAO.selectAll(course);
        // System.out.println(courseList);


        // 8 分页
        // MoocUserDAOImpl moocUserDAO = new MoocUserDAOImpl();
        // List<MoocUser> moocUsers = moocUserDAO.selectAll(BeanFactory.getInstance().getMoocUser());
        // System.out.println(moocUsers);
        // Page<MoocUser> page = moocUserDAO.page(BeanFactory.getInstance().getMoocUser(), 1, 3);
        // System.out.println(page);
    }
}
