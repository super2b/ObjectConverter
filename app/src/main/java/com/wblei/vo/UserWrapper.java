package com.wblei.vo;

import com.wblei.converter_annotation.Converter;
import com.wblei.converter_annotation.PBField;

/**
 * stand for http result.
 * Created by weibolei on 25/03/2018.
 */
@Converter(source = User.class)
public class UserWrapper extends CommonVo {
  private String password;
  private int age;
  @PBField(name = "address")
  private String location;

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return age;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override public String toString() {
    return "name:" + getName() + ", password:" + getPassword() + ", age:" + getAge();
  }
}
