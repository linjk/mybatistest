package cn.linjk.mybatistest.domain;

public class User {
    private Long iduser;
    private String name;
    private String passwd;

    public Long getUserId() {
        return iduser;
    }

    public void setUserId(Long userId) {
        this.iduser = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
