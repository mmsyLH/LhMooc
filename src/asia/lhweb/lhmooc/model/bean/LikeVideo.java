package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 像视频
 * 点赞表
 *
 * @author 罗汉
 * @TableName like_video
 * @date 2024/03/11
 */
public class LikeVideo {
    /**
     * 点赞表的id
     */
    @Id
    private Integer likeid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 视频id
     */
    private Integer videoid;

    /**
     * 0是未点赞 1是点赞
     */
    private Integer isdelete;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;



    /**
     * 点赞表的id
     */
    public Integer getLikeid() {
        return likeid;
    }

    /**
     * 点赞表的id
     */
    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
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
     * 0是未点赞 1是点赞
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * 0是未点赞 1是点赞
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
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

    /**
     * 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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
        LikeVideo other = (LikeVideo) that;
        return (this.getLikeid() == null ? other.getLikeid() == null : this.getLikeid().equals(other.getLikeid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append(", likeid=").append(likeid);
        sb.append(", userid=").append(userid);
        sb.append(", videoid=").append(videoid);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);

        sb.append("]");
        return sb.toString();
    }
}