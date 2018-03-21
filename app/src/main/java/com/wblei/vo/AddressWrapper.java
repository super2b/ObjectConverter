package com.wblei.vo;

import com.wblei.converter_annotation.Converter;

/**
 * A mock class that stand for Http Result Object.
 * Created by supber2b on 13/03/2018.
 */

@Converter(source = Address.class)
public class AddressWrapper extends SimpleWrapper {
  private String province;
  private String city;
  private String location;
  private String other2;
  public void setProvince(String province) {
    this.province = province;
  }

  public String getProvince() {
    return this.province;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city){
    this.city = city;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
