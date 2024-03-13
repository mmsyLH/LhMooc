package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 课程视频
 *
 * @author 罗汉
 * @TableName course_video
 * @date 2024/03/11
 */
public class CourseVideo {
    /**
     * 视频表的id
     */
    @Id
    private Integer videoid;

    /**
     * 视频名称，存储课程视频的名称
     */
    private String videoname;

    /**
     * 章节表的id
     */
    private Integer chapterid;

    /**
     * 视频时长
     */
    private Date videotime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 上传用户的id
     */
    private Integer userid;

    /**
     * 视频链接
     */
    private String videourl;

    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 0表示还没删除 1表示删除
     */
    private Integer isdelete;
    /**
     * 0表示还没删除 1表示删除
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * 0表示还没删除 1表示删除
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }


    /**
     * 视频表的id
     */
    public Integer getVideoid() {
        return videoid;
    }

    /**
     * 视频表的id
     */
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    /**
     * 视频名称，存储课程视频的名称
     */
    public String getVideoname() {
        return videoname;
    }

    /**
     * 视频名称，存储课程视频的名称
     */
    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    /**
     * 章节表的id
     */
    public Integer getChapterid() {
        return chapterid;
    }

    /**
     * 章节表的id
     */
    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    /**
     * 视频时长
     */
    public Date getVideotime() {
        return videotime;
    }

    /**
     * 视频时长
     */
    public void setVideotime(Date videotime) {
        this.videotime = videotime;
    }

    /**
     * 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 上传用户的id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 上传用户的id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 视频链接
     */
    public String getVideourl() {
        return videourl;
    }

    /**
     * 视频链接
     */
    public void setVideourl(String videourl) {
        this.videourl = videourl;
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
        CourseVideo other = (CourseVideo) that;
        return (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getVideoname() == null ? other.getVideoname() == null : this.getVideoname().equals(other.getVideoname()))
            && (this.getChapterid() == null ? other.getChapterid() == null : this.getChapterid().equals(other.getChapterid()))
            && (this.getVideotime() == null ? other.getVideotime() == null : this.getVideotime().equals(other.getVideotime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getVideourl() == null ? other.getVideourl() == null : this.getVideourl().equals(other.getVideourl()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append(", videoid=").append(videoid);
        sb.append(", videoname=").append(videoname);
        sb.append(", chapterid=").append(chapterid);
        sb.append(", videotime=").append(videotime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", userid=").append(userid);
        sb.append(", videourl=").append(videourl);
        sb.append(", createtime=").append(createtime);

        sb.append("]");
        return sb.toString();
    }
}