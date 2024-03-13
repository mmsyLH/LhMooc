package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 交易流水表
 *
 * @author 罗汉
 * @TableName transaction
 * @date 2024/03/11
 */
public class Transaction {
    /**
     * 交易流水表的id
     */
    @Id
    private Integer id;

    /**
     * 流水号
     */
    private String transactionid;

    /**
     * 流水的用户id
     */
    private Integer userid;

    /**
     * 流水类型 1充值 2消费
     */
    private Integer transactiontype;

    /**
     * 交易金额
     */
    private Integer amount;

    /**
     * 交易时间
     */
    private Date transactiontime;

    /**
     * 交易状态 1 成功 2失败 3进行中
     */
    private Integer transactionstatus;

    /**
     * 交易方式 1支付宝 2微信
     */
    private Integer paychannel;

    /**
     * 附加信息
     */
    private String info;

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
     * 交易流水表的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 交易流水表的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 流水号
     */
    public String getTransactionid() {
        return transactionid;
    }

    /**
     * 流水号
     */
    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    /**
     * 流水的用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 流水的用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 流水类型 1充值 2消费
     */
    public Integer getTransactiontype() {
        return transactiontype;
    }

    /**
     * 流水类型 1充值 2消费
     */
    public void setTransactiontype(Integer transactiontype) {
        this.transactiontype = transactiontype;
    }

    /**
     * 交易金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 交易金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 交易时间
     */
    public Date getTransactiontime() {
        return transactiontime;
    }

    /**
     * 交易时间
     */
    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    /**
     * 交易状态 1 成功 2失败 3进行中
     */
    public Integer getTransactionstatus() {
        return transactionstatus;
    }

    /**
     * 交易状态 1 成功 2失败 3进行中
     */
    public void setTransactionstatus(Integer transactionstatus) {
        this.transactionstatus = transactionstatus;
    }

    /**
     * 交易方式 1支付宝 2微信
     */
    public Integer getPaychannel() {
        return paychannel;
    }

    /**
     * 交易方式 1支付宝 2微信
     */
    public void setPaychannel(Integer paychannel) {
        this.paychannel = paychannel;
    }

    /**
     * 附加信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 附加信息
     */
    public void setInfo(String info) {
        this.info = info;
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
        Transaction other = (Transaction) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTransactionid() == null ? other.getTransactionid() == null : this.getTransactionid().equals(other.getTransactionid()))
                && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
                && (this.getTransactiontype() == null ? other.getTransactiontype() == null : this.getTransactiontype().equals(other.getTransactiontype()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getTransactiontime() == null ? other.getTransactiontime() == null : this.getTransactiontime().equals(other.getTransactiontime()))
                && (this.getTransactionstatus() == null ? other.getTransactionstatus() == null : this.getTransactionstatus().equals(other.getTransactionstatus()))
                && (this.getPaychannel() == null ? other.getPaychannel() == null : this.getPaychannel().equals(other.getPaychannel()))
                && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", transactionid=").append(transactionid);
        sb.append(", userid=").append(userid);
        sb.append(", transactiontype=").append(transactiontype);
        sb.append(", amount=").append(amount);
        sb.append(", transactiontime=").append(transactiontime);
        sb.append(", transactionstatus=").append(transactionstatus);
        sb.append(", paychannel=").append(paychannel);
        sb.append(", info=").append(info);
        sb.append("]");
        return sb.toString();
    }
}