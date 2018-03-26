package com.wblei.vo;

/**
 * DEMO Class **********************
 * Created by weibolei on 25/03/2018.
 */

public class UserWrapper_Converter_DEMO {
  public void convert(com.wblei.vo.User source, com.wblei.vo.UserWrapper target) {
    target.setPassword(source.getPassword());
    target.setName(source.getName());
    target.setName(source.getName());
  }
}
