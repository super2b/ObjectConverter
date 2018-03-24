package com.wblei.converter_processor.object;

import javax.lang.model.element.Modifier;

/**
 * Created by weibolei on 25/03/2018.
 */

public class FieldElement {
  private Modifier modifier;
  private String name;

  public void setModifier(Modifier modifier) {
    this.modifier = modifier;
  }

  public Modifier getModifier() {
    return modifier;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override public String toString() {
    return (modifier == null? "" : modifier.toString()) + " " + name;
  }
}
