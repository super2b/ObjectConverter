package com.wblei.vo;

import com.wblei.converter_annotation.Converter;

/**
 * Created by weibolei on 13/03/2018.
 */

@Converter()
public class AddressWrapper {
  private String province;
  private String city;
  private String location;

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
