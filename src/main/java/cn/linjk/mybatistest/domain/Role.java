package cn.linjk.mybatistest.domain;

import java.util.List;

public class Role {
    private Long roleId;
    private String name;
    private String remark;

    // 角色包含的权限列表
    private List<Autority> autorityList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Autority> getAutorityList() {
        return autorityList;
    }

    public void setAutorityList(List<Autority> autorityList) {
        this.autorityList = autorityList;
    }
}
