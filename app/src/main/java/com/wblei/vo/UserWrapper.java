package com.wblei.vo;

import com.wblei.converter_annotation.Converter;

/**
 * stand for http result.
 * Created by weibolei on 25/03/2018.
 */
@Converter(source = User.class)
public class UserWrapper extends CommonVo {

  private String userName;
  private String password;
  private int age;

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return age;
  }
}
