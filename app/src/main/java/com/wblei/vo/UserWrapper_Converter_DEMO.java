package com.wblei.vo;

import com.wblei.converter.IConverter;

/**
 * DEMO Class **********************
 * Created by weibolei on 25/03/2018.
 */

public class UserWrapper_Converter_DEMO  implements IConverter<User, UserWrapper> {
  public UserWrapper convert(com.wblei.vo.User source) {
    UserWrapper target = new UserWrapper();
    target.setPassword(source.getPassword());
    target.setName(source.getName());
    target.setName(source.getName());
    return target;
  }

}
