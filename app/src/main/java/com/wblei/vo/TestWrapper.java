package com.wblei.vo;

import com.wblei.converter_annotation.Converter;

/**
 * Created by weibolei on 26/03/2018.
 */
@Converter(source = Test.class)
public class TestWrapper {
  private int id;
  private String type;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
