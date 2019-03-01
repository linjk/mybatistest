package cn.linjk.mybatistest.domain;

import cn.linjk.mybatistest.type.TypeStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

    private static final long serialVersionUID = -1613112347272882149L;

    private Long userId;
    private String name;
    private String password;
    private Date lastLogin;
    private String remark;
    private TypeStatus status;

    private TypeStatus typeStatus;

    public TypeStatus getStatus() {
        return status;
    }

    public void setStatus(TypeStatus status) {
        this.status = status;
    }

    // 用户与角色一对一，即：用户拥有一个角色
    private Role role;

    // 用户的角色集合
    private List<Role> roleList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
