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
    private Double orderamount;

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

    public Double getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(Double orderamount) {
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

}