package asia.lhweb.lhmooc.model.vo;

import asia.lhweb.lhmooc.annotation.Id;
import asia.lhweb.lhmooc.model.bean.CourseVideo;

import java.util.Date;
import java.util.List;

/**
 * 章课程
 * 课程章节表
 *
 * @author 罗汉
 * @TableName course_chapter
 * @date 2024/03/11
 */
public class CourseChapterVo {
    /**
     * 课程章节表的id
     */
    @Id
    private Integer chapterid;

    /**
     * 章节名称
     */
    private String chaptername;

    /**
     * 课程id
     */
    private Integer couseid;

    /**
     * 创建时间
     */
    private Date createtime;


    /**
     * 0表示还没删除 1表示删除
     */
    private Integer isdelete;

    /**
     * 课程视频列表
     */
    private List<CourseVideo> courseVideoList;

    public List<CourseVideo> getCourseVideoList() {
        return courseVideoList;
    }

    public void setCourseVideoList(List<CourseVideo> courseVideoList) {
        this.courseVideoList = courseVideoList;
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
     * 课程章节表的id
     */
    public Integer getChapterid() {
        return chapterid;
    }

    /**
     * 课程章节表的id
     */
    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    /**
     * 章节名称
     */
    public String getChaptername() {
        return chaptername;
    }

    /**
     * 章节名称
     */
    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    /**
     * 课程id
     */
    public Integer getCouseid() {
        return couseid;
    }

    /**
     * 课程id
     */
    public void setCouseid(Integer couseid) {
        this.couseid = couseid;
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
        CourseChapterVo other = (CourseChapterVo) that;
        return (this.getChapterid() == null ? other.getChapterid() == null : this.getChapterid().equals(other.getChapterid()))
                && (this.getChaptername() == null ? other.getChaptername() == null : this.getChaptername().equals(other.getChaptername()))
                && (this.getCouseid() == null ? other.getCouseid() == null : this.getCouseid().equals(other.getCouseid()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append(", chapterid=").append(chapterid);
        sb.append(", chaptername=").append(chaptername);
        sb.append(", couseid=").append(couseid);
        sb.append(", createtime=").append(createtime);

        sb.append("]");
        return sb.toString();
    }
}