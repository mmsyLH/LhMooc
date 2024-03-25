package asia.lhweb.lhmooc.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程购买记录表
 * @TableName buycourse_history
 */
public class BuycourseHistory {
    /**
     * 购买记录ID
     */
    private Integer id;

    /**
     * 购买用户ID
     */
    private Integer userid;

    /**
     * 课程ID
     */
    private Integer courseid;

    /**
     * 购买时间
     */
    private Date createtime;

    /**
     * 支付金额
     */
    private Double money;

    /**
     * 支付方式 1微信 2支付宝 3余额
     */
    private String paymentmethod;


    /**
     * 购买记录ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 购买记录ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 购买用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 购买用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 课程ID
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 课程ID
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * 购买时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 购买时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 支付金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 支付金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 支付方式 1微信 2支付宝 3余额
     */
    public String getPaymentmethod() {
        return paymentmethod;
    }

    /**
     * 支付方式 1微信 2支付宝 3余额
     */
    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}