package com.wblei.converter_processor.checker;

import java.util.List;
import javax.lang.model.element.Modifier;

/**
 * Created by weibolei on 08/04/2018.
 */

public class CheckRule {
  private List<Modifier> modifiers;
  private String endWith;

  public void setModifiers(List<Modifier> modifiers) {
    this.modifiers = modifiers;
  }

  public List<Modifier> getModifiers() {
    return modifiers;
  }

  public String getEndWith() {
    return endWith;
  }

  public void setEndWith(String endWith) {
    this.endWith = endWith;
  }
}
