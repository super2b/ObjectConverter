package com.weibo.objectconverter;

import com.wblei.converter_processor.ElementHelper;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by weibolei on 22/03/2018.
 */

public class ElementHelperTest {
  private ElementHelper helper;

  @Before public void init() {
    this.helper = ElementHelper.getInstance();
  }

  @Test public void testGetElement() {
    assertEquals("testGetElement", "testGetElement");
  }

  @Test public void testGetSuperClass() {
    assertEquals("testGetSuperClass", "testGetSuperClass");
  }
}
