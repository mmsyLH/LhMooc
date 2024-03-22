package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.dao.LikeCourseDAO;
import asia.lhweb.lhmooc.dao.impl.LikeCourseDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.LikeCourse;
import asia.lhweb.lhmooc.service.LikeCourseService;

/**
 * @author :罗汉
 * @date : 2024/3/13
 */
public class LikeCourseServiceImpl implements LikeCourseService {
    private LikeCourseDAO likeCourseDAO=new LikeCourseDAOImpl();
    @Override
    public Page<LikeCourse> page(LikeCourse likeCourse, int pageNo, int pageSize) {
        return likeCourseDAO.page(likeCourse,pageNo,pageSize);
    }

    @Override
    public boolean realDelete(LikeCourse likeCourse) {
        return likeCourseDAO.realDelete(likeCourse)!=-1;
    }
}
