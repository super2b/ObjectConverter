package com.wblei.converter_processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.wblei.converter_annotation.Converter;
import com.wblei.converter_processor.checker.AnnClassFieldMethodChecker;
import com.wblei.converter_processor.checker.AnnVaClassFieldMethodChecker;
import com.wblei.converter_processor.helper.ElementHelper;
import com.wblei.converter_processor.object.ObjectElements;
import java.io.IOException;
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
       * Get the annotationed class name. -> target class
       */
      List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
      ObjectElements superObjElements = null;
      for (AnnotationMirror annotationMirror : annotationMirrors) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues =
            annotationMirror.getElementValues();
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues
            .entrySet()) {
          Object val = entry.getValue().getValue();
          Element annoElement = ((DeclaredType) val).asElement();
          superObjElements = ElementHelper.getInstance()
              .loopClassAllFields(annoElement, new AnnVaClassFieldMethodChecker());
        }
      }
      log("all fields of annotation class:" + superObjElements.toString());

      log("className:" + element.getSimpleName().toString());
      //Get the package of the annotationed class -> pkg
      log("pkg:" + element.getEnclosingElement().toString());

      // list all the methods and fields

      // Generate a class with package name {pkg} and class name with {target_class_name}_Converter

      // Generate a method
      //  TargetClazz Convert():
      //    List<Field> targetFields;
      //    List<Field> sourceFields;
      //    for(f:targetClassFields){
      //      if (isNormalJavaType(f)) {
      //        if (sourceHasFieldWithSameName) {
      //          try {
      //            generateJavaCode("target.setF(targetClass.f)"）;
      //          } catch(Exception) {
      //            generateJavaCode("target.setF(targetClass.getF()));
      //          }
      //        }
      //      } else if (isList(f)) {
      //        targetRealType = f.getRealType();
      //        sourceRealType = sf.getRealType();
      //        recursionGenerateFor(o1, o2);
      //      }
      //    }
      //

      //
      try {
        generateJavaClass("HelloWorld");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  private void generateJavaClass(String clazzName) throws IOException {
    MethodSpec main = MethodSpec.methodBuilder("main")
        .addParameter(String[].class, "args")
        .addStatement("$T.out.println($S)", System.class, clazzName)
        .build();
    TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld")
        .addMethod(main)
        .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
        .build();
    JavaFile javaFile = JavaFile.builder("com.example.helloworld", typeSpec).build();
    javaFile.writeTo(filter);
  }

  private void log(String msg) {
    mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
  }
}
