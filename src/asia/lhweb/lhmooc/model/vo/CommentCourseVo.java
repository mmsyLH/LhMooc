package asia.lhweb.lhmooc.model.vo;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 课程评论Vo
 *
 * @TableName comment_course
 */
public class CommentCourseVo {
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
     * 评论的课程id
     */
    private Integer courseid;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createtime;

    // 评论用户的用户名
    private String nickname;

    // 评论用户的头像
    private String imgurl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

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