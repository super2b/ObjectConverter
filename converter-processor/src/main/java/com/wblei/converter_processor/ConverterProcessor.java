package com.wblei.converter_processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.wblei.converter_annotation.Converter;
import com.wblei.converter_processor.checker.AnnClassFieldMethodChecker;
import com.wblei.converter_processor.checker.AnnVaClassFieldMethodChecker;
import com.wblei.converter_processor.helper.ElementHelper;
import com.wblei.converter_processor.object.MethodElement;
import com.wblei.converter_processor.object.ObjectElements;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Created by superb2b on 13/03/2018.
 */
@AutoService(Processor.class) public class ConverterProcessor extends AbstractProcessor {
  private Filer filter;
  private Messager mMessager;
  private Elements mElementUtils;
  private MethodElement sourceMethod;

  @Override public synchronized void init(ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    filter = processingEnvironment.getFiler();
    mMessager = processingEnvironment.getMessager();
    mElementUtils = processingEnvironment.getElementUtils();
  }

  @Override public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotations = new LinkedHashSet<>();
    annotations.add(Converter.class.getCanonicalName());
    return annotations;
  }

  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    for (Element element : roundEnvironment.getElementsAnnotatedWith(Converter.class)) {

      /*
       * Get the class name with annotation class. -> source class
       */
      ObjectElements objectElements =
          ElementHelper.getInstance().loopClassAllFields(element, new AnnClassFieldMethodChecker());
      log("all fields of annotated:" + objectElements.toString());

      /*
       * Get the annotated class name. -> target class
       */
      List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
      ObjectElements annObjElements = null;
      for (AnnotationMirror annotationMirror : annotationMirrors) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues =
            annotationMirror.getElementValues();
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues
            .entrySet()) {
          Object val = entry.getValue().getValue();
          Element annElement = ((DeclaredType) val).asElement();
          annObjElements = ElementHelper.getInstance()
              .loopClassAllFields(annElement, new AnnVaClassFieldMethodChecker());
        }
      }
      log("all fields of annotation class:" + annObjElements.toString());

      try {
        //Get the package of the annotated class -> pkg
        generateJavaClass(annObjElements, objectElements);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  ClassName ICONVERTER = ClassName.get("com.wblei.converter", "IConverter");

  private void generateJavaClass(ObjectElements soruceObj, ObjectElements targetObj)
      throws IOException {
    MethodSpec.Builder builder = MethodSpec.methodBuilder("convert")
        .addModifiers(Modifier.PUBLIC)
        .addParameter(soruceObj.getClassName(), "source")
        .addStatement("$T target = new $T()", targetObj.getClassName(), targetObj.getClassName())
        .returns(targetObj.getClassName());
    brewCode(builder, soruceObj, targetObj);
    builder.addStatement("return target");
    MethodSpec main = builder.build();

    ParameterizedTypeName pt = ParameterizedTypeName.get(ICONVERTER,
        TypeVariableName.get(soruceObj.getClassName().simpleName()),
        TypeVariableName.get(targetObj.getClassName().simpleName()));
    TypeSpec typeSpec = TypeSpec.classBuilder(targetObj.getClassName().simpleName() + "_Converter")
        .addMethod(main)
        .addSuperinterface(pt)
        .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
        .build();
    JavaFile javaFile = JavaFile.builder(targetObj.getClassName().packageName(), typeSpec)
        .addFileComment("Generated code from ObjectConverter. Do not modify")
        .build();
    javaFile.writeTo(filter);
  }

  private void brewCode(MethodSpec.Builder builder, ObjectElements sourceObj,
      ObjectElements targetObj) {
    StringBuilder sb = null;
    List<MethodElement> targetMethods = targetObj.getMethods();
    List<MethodElement> sourceMethods = sourceObj.getMethods();
    for (MethodElement m : targetMethods) {
      int index = -1;
      //Ignore the getter method.
      if (m.getName().startsWith(Constant.GET_METHOD_PREFIX)) {
        continue;
      }
      Map<String, String> map = targetObj.getMappingField();
      for (int i = 0; i < sourceMethods.size(); i++) {
        sourceMethod = sourceMethods.get(i);
        if (sourceMethod.getName().startsWith(Constant.SET_METHOD_PREFIX)) {
          continue;
        }
        if (sb == null) {
          sb = new StringBuilder(sourceMethod.getName());
        } else {
          sb.replace(0, sb.length(), sourceMethod.getName());
        }
        String sourceField = sb.substring(3);
        sb.replace(0, sb.length(), m.getName());
        String targetField = sb.substring(3);
        if (map != null) {
          String tmp = map.get(targetField.toLowerCase());
          if (tmp != null && tmp.trim().length() > 0) {
            targetField = tmp;
          }
        }
        if (sourceField.equalsIgnoreCase(targetField)) {
          index = i;
          builder.addStatement("target.$L(source.$L())", m.getName(),
              sourceMethod.getName());
          break;
        }
      }
    }
  }

  private void log(String msg) {
    mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
  }
}
