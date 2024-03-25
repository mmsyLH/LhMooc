package asia.lhweb.lhmooc.model.vo;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * 课程Vo返回对象
 *
 * @author 罗汉
 * @TableName course
 * @date 2024/03/11
 */
public class CourseVo {
    /**
     * 课程表Id
     */
    @Id
    private Integer courseid;

    /**
     * 课程名
     */
    private String coursename;

    /**
     * 课程分类id 是课程分类表的主键
     */
    private Integer categoryid;

    /**
     * 课程介绍
     */
    private String profile;

    /**
     * 课程价格
     */
    private Double price;

    /**
     * 课程封面的图片
     */
    private String imgurl;

    /**
     * 0表示还没删除 1表示删除
     */
    private Integer isdelete;
    /**
     * 收藏数
     */
    private Integer followCount;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 视频数
     */
    private Integer videoCount;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 课程章节表 列表
     */
    private List<CourseChapterVo> courseChapterList;

    /**
     * 评论列表
     */

    private List<CommentCourseVo> commentCourseVoList;

    public List<CourseChapterVo> getCourseChapterList() {
        return courseChapterList;
    }

    public List<CommentCourseVo> getCommentCourseVoList() {
        return commentCourseVoList;
    }

    public void setCommentCourseVoList(List<CommentCourseVo> commentCourseVoList) {
        this.commentCourseVoList = commentCourseVoList;
    }

    public void setCourseChapterList(List<CourseChapterVo> courseChapterList) {
        this.courseChapterList = courseChapterList;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }


    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

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
     * 课程表Id
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 课程表Id
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * 课程名
     */
    public String getCoursename() {
        return coursename;
    }

    /**
     * 课程名
     */
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    /**
     * 课程分类id 是课程分类表的主键
     */
    public Integer getCategoryid() {
        return categoryid;
    }

    /**
     * 课程分类id 是课程分类表的主键
     */
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * 课程介绍
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 课程介绍
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 课程封面的图片
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 课程封面的图片
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }


}