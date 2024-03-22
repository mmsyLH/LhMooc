package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.CourseVideoDAO;
import asia.lhweb.lhmooc.model.bean.CourseVideo;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class CourseVideoDAOImpl  extends BasicDAO<CourseVideo> implements CourseVideoDAO {

    @Override
    public CourseVideo selectOneById(CourseVideo courseVideoTemp) {
        return super.selectOneById(courseVideoTemp);
    }

}
