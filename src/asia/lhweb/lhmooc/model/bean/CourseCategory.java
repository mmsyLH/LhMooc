package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 课程类别
 * 课程分类表
 *
 * @author 罗汉
 * @TableName course_category
 * @date 2024/03/11
 */
public class CourseCategory {
    /**
     * 课程分类表的id
     */
    @Id
    private Integer categoryid;

    /**
     * 课程分类名
     */
    private String categoryname;

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
     * 课程分类表的id
     */
    public Integer getCategoryid() {
        return categoryid;
    }

    /**
     * 课程分类表的id
     */
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * 课程分类名
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * 课程分类名
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
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
        CourseCategory other = (CourseCategory) that;
        return (this.getCategoryid() == null ? other.getCategoryid() == null : this.getCategoryid().equals(other.getCategoryid()))
            && (this.getCategoryname() == null ? other.getCategoryname() == null : this.getCategoryname().equals(other.getCategoryname()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append(", categoryid=").append(categoryid);
        sb.append(", categoryname=").append(categoryname);
        sb.append(", createtime=").append(createtime);

        sb.append("]");
        return sb.toString();
    }
}