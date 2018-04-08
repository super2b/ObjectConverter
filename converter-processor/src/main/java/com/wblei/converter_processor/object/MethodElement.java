package com.wblei.converter_processor.object;

import javax.lang.model.element.Modifier;

/**
 * Created by weibolei on 25/03/2018.
 */

public class MethodElement {
  private String name;
  private Modifier modifier;
  private String returnType;
  private String parameterType;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setModifier(Modifier modifier) {
    this.modifier = modifier;
  }

  public Modifier getModifier() {
    return modifier;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setParameterType(String parameterType) {
    this.parameterType = parameterType;
  }

  public String getParameterType() {
    return parameterType;
  }

  @Override public String toString() {
    return (modifier == null? "" : modifier.toString())+ " <" + returnType + "> " + name;
  }
}
