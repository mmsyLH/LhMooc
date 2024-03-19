package asia.lhweb.lhmooc.dao;

import asia.lhweb.lhmooc.model.bean.CourseChapter;

import java.util.List;

/**
 * 课程章节DAO
 *
 * @author Administrator
 * @description 针对表【course_chapter(课程章节表)】的数据库操作DAO
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseChapterDAO {

    /**
     * 选择所有
     *
     * @param courseChapter 章课程
     * @return {@link List}<{@link CourseChapter}>
     */
    List<CourseChapter> selectAll(CourseChapter courseChapter);
}
