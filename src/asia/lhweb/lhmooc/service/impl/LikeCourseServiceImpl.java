package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.LikeCourseDAO;
import asia.lhweb.lhmooc.dao.impl.LikeCourseDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.LikeCourse;
import asia.lhweb.lhmooc.service.LikeCourseService;

import java.util.List;

/**
 * @author :罗汉
 * @date : 2024/3/13
 */
public class LikeCourseServiceImpl implements LikeCourseService {
    private LikeCourseDAO likeCourseDAO = new LikeCourseDAOImpl();

    @Override
    public Page<LikeCourse> page(LikeCourse likeCourse, int pageNo, int pageSize) {
        return likeCourseDAO.page(likeCourse, pageNo, pageSize);
    }

    @Override
    public boolean realDelete(LikeCourse likeCourse) {
        return likeCourseDAO.realDelete(likeCourse) != -1;
    }

    /**
     * 添加点赞
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return 添加点赞结果
     */
    @Override
    public Result likeAdd(int userId, int courseId) {
        LikeCourse likeCourse = new LikeCourse();
        likeCourse.setUserid(userId);
        likeCourse.setCourseid(courseId);
        List<LikeCourse> likeCourseList = likeCourseDAO.selectAll(likeCourse);
        if (!likeCourseList.isEmpty()) {
            likeCourseDAO.realDelete(likeCourseList.get(0));
            return Result.success("您已经点赞过了呦 这边帮您取消点赞");
        }
        int result = likeCourseDAO.save(likeCourse); // 假设 save 方法返回影响的行数

        if (result == 1) {
            return Result.success("点赞成功");
        } else {
            return Result.error("点赞失败");
        }
    }

}
