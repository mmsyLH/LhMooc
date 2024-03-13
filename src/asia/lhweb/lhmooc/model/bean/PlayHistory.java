package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 *  支付记录表
 *
 * @author 罗汉
 * @TableName play_history
 * @date 2024/03/11
 */
public class PlayHistory {
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
        PlayHistory other = (PlayHistory) that;
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