package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 订单
 * 订单表
 *
 * @author 罗汉
 * @TableName orders
 * @date 2024/03/11
 */
public class Orders{
    /**
     * 
     */
    @Id
    private Integer id;

    /**
     * 订单id
     */
    private String orderid;

    /**
     * 订单状态 0待付款 1付款
     */
    private Integer orderstatus;

    /**
     * 订单金额
     */
    private Integer orderamount;

    /**
     * 订单的课程id
     */
    private Integer courseid;

    /**
     * 订单时间
     */
    private Date createtime;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 支付渠道 1是支付宝 2是微信
     */
    private Integer paychannel;

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
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 订单id
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 订单id
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 订单状态 0待付款 1付款
     */
    public Integer getOrderstatus() {
        return orderstatus;
    }

    /**
     * 订单状态 0待付款 1付款
     */
    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * 订单金额
     */
    public Integer getOrderamount() {
        return orderamount;
    }

    /**
     * 订单金额
     */
    public void setOrderamount(Integer orderamount) {
        this.orderamount = orderamount;
    }

    /**
     * 订单的课程id
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 订单的课程id
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * 订单时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 订单时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 支付时间
     */
    public Date getPaytime() {
        return paytime;
    }

    /**
     * 支付时间
     */
    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    /**
     * 支付渠道 1是支付宝 2是微信
     */
    public Integer getPaychannel() {
        return paychannel;
    }

    /**
     * 支付渠道 1是支付宝 2是微信
     */
    public void setPaychannel(Integer paychannel) {
        this.paychannel = paychannel;
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
        Orders other = (Orders) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getOrderstatus() == null ? other.getOrderstatus() == null : this.getOrderstatus().equals(other.getOrderstatus()))
            && (this.getOrderamount() == null ? other.getOrderamount() == null : this.getOrderamount().equals(other.getOrderamount()))
            && (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
            && (this.getPaychannel() == null ? other.getPaychannel() == null : this.getPaychannel().equals(other.getPaychannel()));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append("id=").append(id);
        sb.append(", orderid=").append(orderid);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", orderamount=").append(orderamount);
        sb.append(", courseid=").append(courseid);
        sb.append(", createtime=").append(createtime);
        sb.append(", paytime=").append(paytime);
        sb.append(", paychannel=").append(paychannel);

        sb.append("]");
        return sb.toString();
    }
}