package com.wblei.converter_processor.checker;

import com.wblei.converter_processor.Constant;
import com.wblei.converter_processor.object.FieldElement;
import com.wblei.converter_processor.object.MethodElement;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;

/**
 * Check if the fields and related method is complete.
 * From the annotation class' point of view, just need to check if the field has setter method;
 * But for the annotation value class' point of view, need to check if the field has getter method;
 * Created by super2b on 25/03/2018.
 */

public abstract class FieldMethodChecker {
  public void checkFieldsAndMethods(String name, List<FieldElement> fields, List<MethodElement> methods) {
    List<FieldElement> failedFields = new ArrayList<>();
    List<MethodElement> failedMethods = new ArrayList<>();
    for(FieldElement f : fields) {
      List<String> generateMethods = camel(f.getName());

      for (int i = 0; i < methods.size(); i++) {
        MethodElement m = methods.get(i);
        if (generateMethods.get(checkPos()).equals(m.getName())) {
          if (m.getModifier() != Modifier.PUBLIC) {
            failedMethods.add(m);
          }
          break;
        }
        if (i == methods.size() - 1) {
          failedFields.add(f);
        }
      }
    }

    if (failedFields.size() > 0) {
      throw new IllegalArgumentException(
          name + ": the following field need setter method:" + failedFields);
    }

    if (failedMethods.size() > 0) {
      throw new IllegalArgumentException(
          name + ": the following method should be public:" + failedMethods);
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
