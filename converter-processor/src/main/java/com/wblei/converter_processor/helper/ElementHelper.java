package com.wblei.converter_processor.helper;

import com.squareup.javapoet.ClassName;
import com.wblei.converter_annotation.PBField;
import com.wblei.converter_processor.Constant;
import com.wblei.converter_processor.checker.FieldMethodChecker;
import com.wblei.converter_processor.object.FieldElement;
import com.wblei.converter_processor.object.MethodElement;
import com.wblei.converter_processor.object.ObjectElements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

/**
 * Created by superb2b on 22/03/2018.
 */

public class ElementHelper {
  private static ElementHelper instance;

  private ElementHelper() {
  }

  public static ElementHelper getInstance() {
    if (instance == null) {
      instance = new ElementHelper();
    }
    return instance;
  }

  /**
   * looper all the class's(include super classes) fields.
   *
   * @param checker: element and field checker.
   * @return all super's fields and getter|setter methods.
   */
  public ObjectElements loopClassAllFields(Element element, FieldMethodChecker checker) {
    ObjectElements obj = getElement(element);
    Element currentClazzElement = element;
    TypeMirror parentMirror = null;
    while ((parentMirror = ((TypeElement) currentClazzElement).getSuperclass()) != null) {
      if (parentMirror == null || isSdkClass(parentMirror)) {
        break;
      }
      Element superClazzElement = ((DeclaredType) parentMirror).asElement();
      ObjectElements superObj = getElement(superClazzElement);
      obj.appendFields(superObj.getFields());
      if (superObj.getMappingField() != null) {
        if (obj.getMappingField() != null) {
          obj.getMappingField().putAll(superObj.getMappingField());
        } else {
          obj.setMappingField(superObj.getMappingField());
        }
      }

      obj.appendMethods(superObj.getMethods());
      currentClazzElement = superClazzElement;
    }
    checker.checkFieldsAndMethods(obj.getClassName().toString(), obj.getFields(), obj.getMethods());
    return obj;
  }

  /**
   * get the fields and getter/setter method
   *
   * @return ObjectElements object.
   */
  private ObjectElements getElement(Element element) {
    if (element == null) {
      return null;
    }
    Map<String, String> mappingFields = new HashMap<>();
    List<FieldElement> fields = new ArrayList<>();
    List<MethodElement> methods = new ArrayList<>();
    for (Element e : element.getEnclosedElements()) {
      //Only take care of the ExecutableElement and FieldElement.
      String fieldName = e.getSimpleName().toString();
      if (isValidMethod(e, Constant.SET_METHOD_PREFIX, Constant.GET_METHOD_PREFIX)) {
        MethodElement m = new MethodElement();
        Set<Modifier> modifiers = e.getModifiers();
        m.setModifier(modifiers.size() > 0 ? modifiers.iterator().next() : null);
        m.setName(e.getSimpleName().toString());
        //NOTE: Here just care the first parameter.
        if (((ExecutableElement) e).getParameters().size() > 0) {
          String parameterType = ((ExecutableElement) e).getParameters().get(0).asType().toString();
          m.setParameterType(parameterType);
        }

        m.setReturnType(((ExecutableElement) e).getReturnType().toString());
        methods.add(m);
      } else if (e instanceof VariableElement) {
        PBField pbFieldAnnotation = e.getAnnotation(PBField.class);
        FieldElement f = new FieldElement();
        if (pbFieldAnnotation != null) {
          String mapName = pbFieldAnnotation.name();
          // If the PBField annotation name is specified, just replace the exists field.
          mappingFields.put(fieldName.toLowerCase(), mapName);
        }

        f.setName(fieldName);
        Set<Modifier> modifiers = e.getModifiers();
        f.setModifier(modifiers.size() > 0 ? modifiers.iterator().next() : null);

        fields.add(f);
      }
    }
    ObjectElements obj = new ObjectElements();
    if (mappingFields.size() > 0) {
      obj.setMappingField(mappingFields);
    }
    String simpleName = element.getSimpleName().toString();
    ClassName className = ClassName.get(element.getEnclosingElement().toString(), simpleName);
    obj.setClassName(className);
    obj.setFields(fields);
    obj.setMethods(methods);
    return obj;
  }

  /**
   * Check if the method in ExecutableElement starts with one of the methodPrefixes
   *
   * @param element element object
   * @param methodPrefixes method prefix list
   * @return true if the the element is a method and start with one of the method prefixes.
   */
  private boolean isValidMethod(Element element, String... methodPrefixes) {
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
   * Check if the typemirror reference to a sdk class.
   *
   * @return true if it's sdk class.
   */
  private boolean isSdkClass(TypeMirror typeMirror) {
    if (typeMirror == null) {
      throw new IllegalArgumentException("wrong argument");
    }
    return typeMirror.toString().startsWith("java") || typeMirror.toString().startsWith("android.");
  }
}
