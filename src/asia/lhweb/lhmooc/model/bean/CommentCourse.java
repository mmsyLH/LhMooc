package asia.lhweb.lhmooc.model.bean;

import java.util.Date;

/**
 * 课程评论表
 * @TableName comment_course
 */
public class CommentCourse {
    /**
     * 视频评论id
     */
    private Integer commentid;

    /**
     * 评论用户的id
     */
    private Integer userid;

    /**
     * 评论的课程id
     */
    private Integer courseid;

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
     * 评论的课程id
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 评论的课程id
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
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
}