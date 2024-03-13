package asia.lhweb.lhmooc.model.bean;

/**
 * 管理员表
 * @TableName mooc_admin
 */
public class MoocAdmin {
    /**
     * Id
     */
    private Integer id;

    /**
     * 管理员账号
     */
    private String account;

    /**
     * 管理员密码
     */
    private Integer password;

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
     * 管理员账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 管理员账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 管理员密码
     */
    public Integer getPassword() {
        return password;
    }

    /**
     * 管理员密码
     */
    public void setPassword(Integer password) {
        this.password = password;
    }
}