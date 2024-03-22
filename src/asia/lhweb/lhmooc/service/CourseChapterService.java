package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseChapter;

/**
 * 课程章节服务
 *
 * @author Administrator
 * @description 针对表【course_chapter(课程章节表)】的数据库操作Service
 * @createDate 2024-03-11 21:23:21
 * @date 2024/03/11
 */
public interface CourseChapterService {

    /**
     * 页面
     *
     * @param courseChapter 章课程
     * @param parseInt      解析int
     * @param parseInt1     解析int1
     * @return {@link Page}<{@link CourseChapter}>
     */
    Page<CourseChapter> pageByAnd(CourseChapter courseChapter, int parseInt, int parseInt1);

    /**
     * 真正删除
     *
     * @param courseChapter 章课程
     * @return boolean
     */
    boolean realDelete(CourseChapter courseChapter);

    /**
     * 更新
     *
     * @param chapterID   章id
     * @param chaptername chaptername
     * @return {@link Result}
     */
    Result update(int chapterID, String chaptername);

    /**
     * 添加
     *
     * @param parseInt    解析int
     * @param chaptername chaptername
     * @return {@link Result}
     */
    Result add(int parseInt, String chaptername);
}
