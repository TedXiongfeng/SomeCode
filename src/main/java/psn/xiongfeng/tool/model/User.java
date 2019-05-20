package psn.xiongfeng.tool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIgnoreProperties({"password"})对父类有效
public class User {

    private String userName;
    //@JsonIgnore
    private String passWord;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    @JsonIgnore
    public String getPassWord() {
        return passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
