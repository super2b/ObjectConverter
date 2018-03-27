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
import java.lang.reflect.ParameterizedType;
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
        generateJavaClass(annObjElements.getClassName(),
            objectElements.getClassName(), annObjElements.getMethods(),
            objectElements.getMethods());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  ClassName ICONVERTER = ClassName.get("com.wblei.converter", "IConverter");

  private void generateJavaClass(ClassName p1, ClassName p2,
      List<MethodElement> source, List<MethodElement> target) throws IOException {
    MethodSpec.Builder builder = MethodSpec.methodBuilder("convert")
        .addModifiers(Modifier.PUBLIC)
        .addParameter(p1, "source")
        .addStatement("$T target = new $T()", p2, p2)
        .returns(p2);
    brewCode(builder, source, target);
    builder.addStatement("return target");
    MethodSpec main = builder.build();

    ParameterizedTypeName pt =
        ParameterizedTypeName.get(ICONVERTER, TypeVariableName.get(p1.simpleName()),
            TypeVariableName.get(p2.simpleName()));
    TypeSpec typeSpec = TypeSpec.classBuilder(p2.simpleName() + "_Converter")
        .addMethod(main)
        .addSuperinterface(pt)
        .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
        .build();
    JavaFile javaFile = JavaFile.builder(p2.packageName(), typeSpec)
        .addFileComment("Generated code from ObjectConverter. Do not modify")
        .build();
    javaFile.writeTo(filter);
  }

  private void brewCode(MethodSpec.Builder builder, List<MethodElement> sourceMethods,
      List<MethodElement> targetMethods) {
    StringBuilder sb = null;
    for (MethodElement m : targetMethods) {
      int index = -1;
      //Ignore the getter method.
      if (m.getName().startsWith(Constant.GET_METHOD_PREFIX)) {
        continue;
      }
      for (int i = 0; i < sourceMethods.size(); i++) {
        if(sourceMethods.get(i).getName().startsWith(Constant.SET_METHOD_PREFIX)) {
          continue;
        }
        if (sb == null ) {
          sb = new StringBuilder(sourceMethods.get(i).getName());
        } else {
          sb.replace(0, sb.length(), sourceMethods.get(i).getName());
        }
        String sourceField = sb.substring(3);
        sb.replace(0, sb.length(), m.getName());
        String targetField = sb.substring(3);
        if (sourceField.equals(targetField)) {
          index = i;
          builder.addStatement("target.$L(source.$L())", m.getName(), sourceMethods.get(i).getName());
          break;
        }
      }
    }
  }

  private void log(String msg) {
    mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
  }
}
