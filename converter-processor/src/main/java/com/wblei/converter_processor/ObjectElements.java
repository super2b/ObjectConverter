package com.wblei.converter_processor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by super2b on 25/03/2018.
 */

public class ObjectElements {
  private String className;
  private List<String> fields;
  private List<String> methods;

  public void setClassName(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

  public void setFields(List<String> fields) {
    this.fields = fields;
  }

  public void appendFields(List<String> fields) {
    if (this.fields == null) {
      this.fields = new ArrayList<>();
    }
    this.fields.addAll(fields);
  }

  public List<String> getFields() {
    return fields;
  }

  public void setMethods(List<String> methods) {
    this.methods = methods;
  }


  public void appendMethods(List<String> methods) {
    if (this.methods == null) {
      this.methods = new ArrayList<>();
    }
    this.methods.addAll(methods);
  }

  public List<String> getMethods() {
    return methods;
  }

  @Override public String toString() {
    return "[className]:" + className + ", [fields]:" + fields + ", [methods]:" + methods;
  }
}
