package com.wblei.converter_processor;

import java.util.ArrayList;
import java.util.List;

/**
 * Check if the fields and related method is complete.
 * From the annotation class' point of view, just need to check if the field has setter method;
 * But for the annotation value class' point of view, need to check if the field has getter method;
 * Created by super2b on 25/03/2018.
 */

public abstract class FieldMethodChecker {
  public void checkFieldsAndMethods(List<String> fields, List<String> methods) {
    List<String> failedFields = new ArrayList<>();
    for(String f : fields) {
      List<String> generateMethods = camel(f);
      if (methods.indexOf(generateMethods.get(checkPos())) < 0) {
        failedFields.add(f);
      }
    }

    if (failedFields.size() > 0) {
      throw new IllegalArgumentException("the following field need setter method:" + failedFields);
    }
  }

  protected abstract int checkPos();

  /**
   * Generate the getter and setter base on the field name.
   * @param s the field name.
   * @return a list: first is setter method, and second is getter method.
   */
  private List<String> camel(String s) {
    if (null == s || "".equals(s)) {
      throw new IllegalArgumentException("the field can not be empty.");
    }
    StringBuilder sb = new StringBuilder(s);
    sb = sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
    int last = sb.length();
    if ("_".equals(sb.subSequence(last - 1, last))) {
      sb.delete(last - 1, last);
    }
    sb.toString();
    List<String> methods = new ArrayList<>();
    methods.add(Constant.SET_METHOD_PREFIX + sb.toString());
    methods.add(Constant.GET_METHOD_PREFIX + sb.toString());
    return methods;
  }
}
