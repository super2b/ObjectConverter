package com.wblei.vo;

/**
 * Stand for pb object.
 * Created by weibolei on 25/03/2018.
 */

public class User {
  private int age_;
  private String name_;
  private String password_;
  private String address;

  public User(String name, String password, int age) {
    this.age_ = age;
    this.name_ = name;
    this.password_ = password;
  }

  public String getName() {
    return this.name_;
  }

  public String getPassword() {
    return this.password_;
  }

  public int getAge() {
    return this.age_;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
