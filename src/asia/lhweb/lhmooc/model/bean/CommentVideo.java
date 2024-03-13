package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 评论视频
 * 视频评论表
 *
 * @author 罗汉
 * @TableName comment_video
 * @date 2024/03/11
 */
public class CommentVideo {
    /**
     * 视频评论id
     */
    @Id
    private Integer commentid;

    /**
     * 评论用户的id
     */
    private Integer userid;

    /**
     * 评论的视频id
     */
    private Integer videoid;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论是否删除 0表示未删除 1表示删除
     */
    private Integer isdelete;

    /**
     * 评论时间
     */
    private Date createtime;


    /**
     * 视频评论id
     */
    public Integer getCommentid() {
        return commentid;
    }

    /**
     * 视频评论id
     */
    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    /**
     * 评论用户的id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 评论用户的id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 评论的视频id
     */
    public Integer getVideoid() {
        return videoid;
    }

    /**
     * 评论的视频id
     */
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    /**
     * 评论的内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论的内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 评论是否删除 0表示未删除 1表示删除
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * 评论是否删除 0表示未删除 1表示删除
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * 评论时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 评论时间
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
        CommentVideo other = (CommentVideo) that;
        return (this.getCommentid() == null ? other.getCommentid() == null : this.getCommentid().equals(other.getCommentid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(", commentid=").append(commentid);
        sb.append(", userid=").append(userid);
        sb.append(", videoid=").append(videoid);
        sb.append(", content=").append(content);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}