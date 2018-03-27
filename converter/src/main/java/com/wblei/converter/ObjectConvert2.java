package com.wblei.converter;

/**
 * Created by weibolei on 27/03/2018.
 */

public class ObjectConvert2 {
  public Object tryToConvert(String clazzName, Object sourceObj) {
    try {
      Class clazz = Class.forName(clazzName + "_Converter");
      Object obj = clazz.newInstance();
      if (obj instanceof IConverter) {
        return ((IConverter)obj).convert(sourceObj);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
    return null;
  }
}
