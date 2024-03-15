package asia.lhweb.lhmooc.model.vo;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 课程
 * 课程表
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
    private Integer price;

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

    /**
     * 课程价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 课程价格
     */
    public void setPrice(Integer price) {
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
        CourseVo other = (CourseVo) that;
        return (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()))
                && (this.getCoursename() == null ? other.getCoursename() == null : this.getCoursename().equals(other.getCoursename()))
                && (this.getCategoryid() == null ? other.getCategoryid() == null : this.getCategoryid().equals(other.getCategoryid()))
                && (this.getProfile() == null ? other.getProfile() == null : this.getProfile().equals(other.getProfile()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getImgurl() == null ? other.getImgurl() == null : this.getImgurl().equals(other.getImgurl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseid() == null) ? 0 : getCourseid().hashCode());
        result = prime * result + ((getCoursename() == null) ? 0 : getCoursename().hashCode());
        result = prime * result + ((getCategoryid() == null) ? 0 : getCategoryid().hashCode());
        result = prime * result + ((getProfile() == null) ? 0 : getProfile().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getImgurl() == null) ? 0 : getImgurl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(", courseid=").append(courseid);
        sb.append(", coursename=").append(coursename);
        sb.append(", categoryid=").append(categoryid);
        sb.append(", profile=").append(profile);
        sb.append(", price=").append(price);
        sb.append(", imgurl=").append(imgurl);
        sb.append("]");
        return sb.toString();
    }
}