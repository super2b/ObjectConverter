package com.wblei.converter_processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.wblei.converter_annotation.Converter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
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
      String className = null;
      TypeMirror value = null;
      try {
        className = element.getAnnotation(Converter.class).source().toString();
      } catch (MirroredTypeException e) {
        value = e.getTypeMirror();
        if (value != null) {
          className = value.toString();
        }
      }
      for (Element e : element.getEnclosedElements()) {
        log(element.getSimpleName() + "." + e.getSimpleName());
      }
      log("类名:" + element.getSimpleName().toString());
      log("包名:" + element.getEnclosingElement().toString());
      log("注解的类:" + className);
      //Get the annotationed class name. -> target class

      //Get the class name with annotation class. -> source class

      //Get the package of the annotationed class -> pkg

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
