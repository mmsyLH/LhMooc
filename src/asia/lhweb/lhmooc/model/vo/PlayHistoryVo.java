package asia.lhweb.lhmooc.model.vo;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 *  观看记录表
 *
 * @author 罗汉
 * @TableName play_history
 * @date 2024/03/11
 */
public class PlayHistoryVo {
    /**
     * 播放表的id
     */
    @Id
    private Integer playid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 视频id
     */
    private Integer videoid;

    /**
     * 播放进度表
     */
    private Date playprogress;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程简介
     */
    private String courseProfile;

    /**
     * 视频网址
     */
    private String videoUrl;

    /**
     * 课程类别
     */
    private String courCategoryName;
    /**
     * 课程章名
     */
    private String courChapterName;

    /**
     * 视频时间
     */
    private Date videoTime;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseProfile() {
        return courseProfile;
    }

    public void setCourseProfile(String courseProfile) {
        this.courseProfile = courseProfile;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCourCategoryName() {
        return courCategoryName;
    }

    public void setCourCategoryName(String courCategoryName) {
        this.courCategoryName = courCategoryName;
    }

    public String getCourChapterName() {
        return courChapterName;
    }

    public void setCourChapterName(String courChapterName) {
        this.courChapterName = courChapterName;
    }

    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
    }

    /**
     * 播放表的id
     */
    public Integer getPlayid() {
        return playid;
    }

    /**
     * 播放表的id
     */
    public void setPlayid(Integer playid) {
        this.playid = playid;
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
     * 视频id
     */
    public Integer getVideoid() {
        return videoid;
    }

    /**
     * 视频id
     */
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    /**
     * 播放进度表
     */
    public Date getPlayprogress() {
        return playprogress;
    }

    /**
     * 播放进度表
     */
    public void setPlayprogress(Date playprogress) {
        this.playprogress = playprogress;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PlayHistoryVo other = (PlayHistoryVo) that;
        return (this.getPlayid() == null ? other.getPlayid() == null : this.getPlayid().equals(other.getPlayid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getPlayprogress() == null ? other.getPlayprogress() == null : this.getPlayprogress().equals(other.getPlayprogress()));
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append(", playid=").append(playid);
        sb.append(", userid=").append(userid);
        sb.append(", videoid=").append(videoid);
        sb.append(", playprogress=").append(playprogress);

        sb.append("]");
        return sb.toString();
    }
}