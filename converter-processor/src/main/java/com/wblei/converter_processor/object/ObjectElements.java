package com.wblei.converter_processor.object;

import com.squareup.javapoet.ClassName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by super2b on 25/03/2018.
 */

public class ObjectElements {
  private ClassName className;
  private List<FieldElement> fields;
  private List<MethodElement> methods;

  public void setClassName(ClassName className) {
    this.className = className;
  }

  public ClassName getClassName() {
    return className;
  }

  public void setFields(List<FieldElement> fields) {
    this.fields = fields;
  }

  public void appendFields(List<FieldElement> fields) {
    if (this.fields == null) {
      this.fields = new ArrayList<>();
    }
    this.fields.addAll(fields);
  }

  public List<FieldElement> getFields() {
    return fields;
  }

  public void setMethods(List<MethodElement> methods) {
    this.methods = methods;
  }


  public void appendMethods(List<MethodElement> methods) {
    if (this.methods == null) {
      this.methods = new ArrayList<>();
    }
    this.methods.addAll(methods);
  }

  public List<MethodElement> getMethods() {
    return methods;
  }

  @Override public String toString() {
    return "[className]:" + className + ", [fields]:" + fields + ", [methods]:" + methods;
  }
}
