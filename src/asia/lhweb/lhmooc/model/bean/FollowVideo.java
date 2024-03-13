package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 跟着视频
 * 视频收藏表
 *
 * @author 罗汉
 * @TableName follow_video
 * @date 2024/03/11
 */
public class FollowVideo  {
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
     * 视频id
     */
    private Integer videoid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 0是未收藏 1是已经收藏
     */
    private Integer isdelete;



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
     * 0是未收藏 1是已经收藏
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * 0是未收藏 1是已经收藏
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
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
        FollowVideo other = (FollowVideo) that;
        return (this.getFollowid() == null ? other.getFollowid() == null : this.getFollowid().equals(other.getFollowid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()));
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append(", followid=").append(followid);
        sb.append(", userid=").append(userid);
        sb.append(", videoid=").append(videoid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", isdelete=").append(isdelete);

        sb.append("]");
        return sb.toString();
    }
}