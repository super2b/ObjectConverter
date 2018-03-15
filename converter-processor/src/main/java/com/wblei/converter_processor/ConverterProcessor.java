package com.wblei.converter_processor;

import com.google.auto.service.AutoService;
import com.wblei.converter_annotation.Converter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Created by superb2b on 13/03/2018.
 */
@AutoService(Processor.class) public class ConverterProcessor extends AbstractProcessor {
  private Filer mFiler;
  private Messager mMessager;
  private Elements mElementUtils;

  @Override public synchronized void init(ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    mFiler = processingEnvironment.getFiler();
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
      log("类名:"+element.getSimpleName().toString());
      log("包名:" + element.getEnclosingElement().toString());
      log("注解的类:" + className);
    }
    return false;
  }


  private void log(String msg) {
    mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
  }

}
