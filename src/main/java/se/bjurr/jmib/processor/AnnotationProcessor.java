package se.bjurr.jmib.processor;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.logging.Level.SEVERE;

import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

public class AnnotationProcessor extends AbstractProcessor {

  public AnnotationProcessor() {}

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return newHashSet( //
        GenerateMethodInvocationBuilder.class.getName());
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    ElementHandler elementHandler =
        new ElementHandler(
            this.processingEnv.getElementUtils(),
            this.processingEnv.getFiler(),
            this.processingEnv.getMessager());

    Set<? extends Element> elements =
        roundEnv.getElementsAnnotatedWith(GenerateMethodInvocationBuilder.class);
    for (Element element : elements) {
      try {
        elementHandler.handle((TypeElement) element);

      } catch (Exception e) {
        Logger logger = Logger.getLogger(this.getClass().getSimpleName());
        logger.log(SEVERE, e.getMessage(), e);
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
      }
    }

    return false;
  }
}
