package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 课程收藏表
 * @TableName follow_course
 */
public class FollowCourse {
    /**
     * 视频收藏的id
     */
    @Id
    private Integer followid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 课程id
     */
    private Integer courseid;

    /**
     * 创建时间
     */
    private Date createtime;



    /**
     * 视频收藏的id
     */
    public Integer getFollowid() {
        return followid;
    }

    /**
     * 视频收藏的id
     */
    public void setFollowid(Integer followid) {
        this.followid = followid;
    }

    /**
     * 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 课程id
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 课程id
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}