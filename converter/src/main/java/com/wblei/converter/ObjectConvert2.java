package com.wblei.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weibolei on 27/03/2018.
 */

public class ObjectConvert2 {
  public static Object tryToConvert(String clazzName, Object sourceObj) {
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

  public static <T> List<T> tryToConvert(Class clazz, List<? extends Object> sourceOfList) {
    List<T> resultList = new ArrayList<>();
    try {
      Class converterClazz = Class.forName(clazz.getName() + "_Converter");
      Object obj = converterClazz.newInstance();
      if (obj instanceof IConverter) {
        for (Object sourceObj : sourceOfList) {
          T t = (T)((IConverter)obj).convert(sourceObj);
          resultList.add(t);
        }
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
    return resultList;
  }
}
