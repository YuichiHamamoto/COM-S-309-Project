package com.spring.backend.app;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="login_info")
public class LoginInfo {
    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    public LoginInfo(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public LoginInfo() {};

    @ApiModelProperty(value = "username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty(value = "user's password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "user email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
