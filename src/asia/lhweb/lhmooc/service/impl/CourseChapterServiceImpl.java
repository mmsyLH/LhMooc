package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.CourseChapterDAO;
import asia.lhweb.lhmooc.dao.impl.CourseChapterDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseChapter;
import asia.lhweb.lhmooc.service.CourseChapterService;

/**
 * 课程章服务实现
 *
 * @author 罗汉
 * @date 2024/03/12
 */
public class CourseChapterServiceImpl implements CourseChapterService {
    private CourseChapterDAO courseChapterDAO = new CourseChapterDAOImpl();

    /**
     * 页面
     *
     * @param courseChapter 章课程
     * @param pageNo        页面没有
     * @param pageSize      页面大小
     * @return {@link Page}<{@link CourseChapter}>
     */
    @Override
    public Page<CourseChapter> pageByAnd(CourseChapter courseChapter, int pageNo, int pageSize) {
        return courseChapterDAO.pageByAnd(courseChapter, pageNo, pageSize);
    }

    /**
     * 真正删除
     *
     * @param courseChapter 章课程
     * @return boolean
     */
    @Override
    public boolean realDelete(CourseChapter courseChapter) {
        return courseChapterDAO.realDelete(courseChapter) != -1;
    }

    @Override
    public Result update(int chapterID, String chaptername) {
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setChapterid(chapterID);
        CourseChapter findCourseChapter = courseChapterDAO.selectOneById(courseChapter);
        if (findCourseChapter == null) {
            return Result.error("章节不存在");
        }
        findCourseChapter.setChaptername(chaptername);

        return courseChapterDAO.update(findCourseChapter) != 1 ? Result.success("修改成功") : Result.error("修改失败");
    }

}
