package com.wblei.converter_processor;


/**
 * Setter method is required.
 * Created by weibolei on 25/03/2018.
 */

public class AnnClassFieldMethodChecker extends FieldMethodChecker {

  // mean the index of the camel method's return string array.
  @Override protected int checkPos() {
    return 0; // mean setter method.
  }
}
