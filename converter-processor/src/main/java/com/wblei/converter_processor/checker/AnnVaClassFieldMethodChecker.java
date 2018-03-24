package com.wblei.converter_processor.checker;

/**
 * Created by weibolei on 25/03/2018.
 */

public class AnnVaClassFieldMethodChecker extends FieldMethodChecker {

  // mean the index of the camel method's return string array.
  @Override protected int checkPos() {
    return 1; // mean getter method.
  }
}
