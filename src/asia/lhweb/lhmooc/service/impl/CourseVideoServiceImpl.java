package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.CourseVideoDAO;
import asia.lhweb.lhmooc.dao.impl.CourseVideoDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseVideo;
import asia.lhweb.lhmooc.service.CourseVideoService;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class CourseVideoServiceImpl implements CourseVideoService {
    //课程视频dao
    private CourseVideoDAO courseVideoDAO=new CourseVideoDAOImpl();

    /**
     * 真正删除
     *
     * @param courseVideoId 课程视频编号
     * @return boolean
     */
    @Override
    public boolean realDelete(int courseVideoId) {
        CourseVideo courseVideo = new CourseVideo();
        courseVideo.setVideoid(courseVideoId);
        return courseVideoDAO.realDelete(courseVideo)!=-1;
    }

    /**
     * 逐页和
     *
     * @param courseVideo 课程视频
     * @param pageNo      页面没有
     * @param pageSize    页面大小
     * @return {@link Page}<{@link CourseVideo}>
     */
    @Override
    public Page<CourseVideo> pageByAnd(CourseVideo courseVideo, int pageNo, int pageSize) {
        return courseVideoDAO.pageByAnd(courseVideo,pageNo,pageSize);
    }

    /**
     * 添加
     *
     * @param courseVideo 课程视频
     * @return {@link Result}
     */
    @Override
    public Result add(CourseVideo courseVideo) {
        return courseVideoDAO.save(courseVideo)!=-1?Result.success("添加视频成功"):Result.error("添加视频失败");
    }

    /**
     * 更新
     *
     * @param videoid   videoid
     * @param videoname videoname
     * @param videourl  videourl
     * @return {@link Result}
     */
    @Override
    public Result update(int videoid, String videoname, String videourl) {
        CourseVideo courseVideo = new CourseVideo();
        courseVideo.setVideoid(videoid);
        CourseVideo findCourseVideo=courseVideoDAO.selectOneById(courseVideo);
        if(findCourseVideo!=null){
            findCourseVideo.setVideoname(videoname);
            findCourseVideo.setVideourl(videourl);
            return courseVideoDAO.update(findCourseVideo)!=-1?Result.success("更新视频成功"):Result.error("更新视频失败");
        }else {
            return Result.error("更新视频失败 id不存在");
        }
    }

}
