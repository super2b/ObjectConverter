package com.wblei.converter_processor;

import com.wblei.converter_annotation.PBField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

/**
 * Created by superb2b on 22/03/2018.
 */

public class ElementHelper {
  private static ElementHelper instance;
  private static final String SET_METHOD_PREFIX = "set";
  private static final String GET_METHOD_PREFIX = "get";

  private ElementHelper() {
  }

  public static ElementHelper getInstance() {
    if (instance == null) {
      instance = new ElementHelper();
    }
    return instance;
  }

  /**
   * get the fields and getter/setter method
   * @param element
   * @return a map with classname as key, and list of fields & getter|setter methods as value.
   */
  private Map<String, List<String>> getElement(Element element) {
    if (element == null) {
      return null;
    }
    Map<String, List<String>> map = new HashMap<>();
    List<String> fields = new ArrayList<>();
    for (Element e : element.getEnclosedElements()) {
      //Only take care of the ExecutableElement and VariableElement.
      String fieldName = e.getSimpleName().toString();

      if (isValidMethod(e, SET_METHOD_PREFIX, GET_METHOD_PREFIX)
          || (e instanceof VariableElement)) {
        PBField pbFieldAnnotation = e.getAnnotation(PBField.class);
        if (pbFieldAnnotation != null) {
          String mapName = pbFieldAnnotation.name();
          // If the PBField annotation name is specified, just replace the exists field.
          if (null != mapName && !"".equals(mapName)) {
            fieldName = mapName;
          }
        }
        fields.add(fieldName);
      }
    }
    map.put(element.getSimpleName().toString(), fields);
    return map;
  }

  /**
   * Check if the method in ExecutableElement starts with one of the methodPrefixes
   * @param element element object
   * @param methodPrefixes method prefix list
   * @return true if the the element is a method and start with one of the method prefixes.
   */
  private boolean isValidMethod(Element element, String ... methodPrefixes) {
    if (element instanceof ExecutableElement) {
      String fieldName = element.getSimpleName().toString();
      for (String prefix : methodPrefixes) {
        if (fieldName.startsWith(prefix)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * looper all the class's(include super classes) fields.
   * @param element
   * @return all super's fields and getter|setter methods.
   */
  public Map<String, List<String>> loopClassAllFields(Element element) {
    Map<String, List<String>> map = new HashMap();
    map.putAll(getElement(element));
    Element currentClazzElement = element;
    TypeMirror parentMirror = null;
    while ((parentMirror = ((TypeElement) currentClazzElement).getSuperclass()) != null) {
      if (parentMirror == null || isSdkClass(parentMirror)) {
        break;
      }
      Element superClazzElement = ((DeclaredType) parentMirror).asElement();
      map.putAll(getElement(superClazzElement));
      currentClazzElement = superClazzElement;
    }
    return map;
  }

  /**
   * Check if the typemirror reference to a sdk class.
   * @param typeMirror
   * @return true if it's sdk class.
   */
  private boolean isSdkClass(TypeMirror typeMirror) {
    if (typeMirror == null) {
      throw new IllegalArgumentException("wrong argument");
    }
    return typeMirror.toString().startsWith("java") || typeMirror.toString().startsWith("android.");
  }

}
