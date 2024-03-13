package asia.lhweb.lhmooc.model.bean;

import asia.lhweb.lhmooc.annotation.Id;

import java.util.Date;

/**
 * 蕴藏用户
 * 慕课用户信息表
 *
 * @author 罗汉
 * @TableName mooc_user
 * @date 2024/03/11
 */
public class MoocUser{
    /**
     * 自动增长的id
     */
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 头像链接,相对路径(相对于项目的路径)
     */
    private String avatar;



    /**
     * 钱包余额 单位元,用户的账户余额。初始注册赠送100
     */
    private Double wallet;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 0 表示普通人员 1表示管理员
     */
    private Integer userrole;
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
     * 自动增长的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自动增长的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 头像链接,相对路径(相对于项目的路径)
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像链接,相对路径(相对于项目的路径)
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    /**
     * 钱包余额 单位元,用户的账户余额。初始注册赠送100
     */
    public Double getWallet() {
        return wallet;
    }

    /**
     * 钱包余额 单位元,用户的账户余额。初始注册赠送100
     */
    public void setWallet(Double wallet) {
        this.wallet = wallet;
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

    /**
     * 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 0 表示普通人员 1表示管理员
     */
    public Integer getUserrole() {
        return userrole;
    }

    /**
     * 0 表示普通人员 1表示管理员
     */
    public void setUserrole(Integer userrole) {
        this.userrole = userrole;
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
        MoocUser other = (MoocUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()))
            && (this.getWallet() == null ? other.getWallet() == null : this.getWallet().equals(other.getWallet()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getUserrole() == null ? other.getUserrole() == null : this.getUserrole().equals(other.getUserrole()));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append("id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", email=").append(email);
        sb.append(", avatar=").append(avatar);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", wallet=").append(wallet);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", userrole=").append(userrole);

        sb.append("]");
        return sb.toString();
    }
}