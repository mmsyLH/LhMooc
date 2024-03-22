package asia.lhweb.lhmooc.service;

import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.LikeCourse;

/**
* @author Administrator
* @description 针对表【like_course(课程点赞表)】的数据库操作Service
* @createDate 2024-03-13 19:57:04
*/
public interface LikeCourseService {

    Page<LikeCourse> page(LikeCourse likeCourse, int pageNo, int pageSize);

    boolean realDelete(LikeCourse likeCourse);
}
