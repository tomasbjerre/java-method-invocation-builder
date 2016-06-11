package se.bjurr.jmib.test;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import se.bjurr.jmib.processor.AnnotationProcessor;

public abstract class AnnotationProcessingTestUtil {

 public static void processClasses(Iterable<? extends File> filesToCompile, List<Class<?>> classesToProcess)
   throws IOException {
  JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

  DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

  StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

  List<String> classnamesForProcessing = newArrayList();
  for (Class<?> classToTest : classesToProcess) {
   classnamesForProcessing.add(classToTest.getName());
  }

  List<String> optionList = newArrayList(//
    "-processor", AnnotationProcessor.class.getName()//
    , "-parameters"//
    , "-s", "src/test/generated");

  Writer writer = new StringWriter();

  Iterable<? extends JavaFileObject> toCompile = fileManager.getJavaFileObjectsFromFiles(filesToCompile);

  CompilationTask task = compiler.getTask(writer, fileManager, diagnostics, optionList, classnamesForProcessing,
    toCompile);

  assertThat(task.call())//
    .as(writer.toString() + '\n' + on('\n').join(diagnostics.getDiagnostics()))//
    .isTrue();

  fileManager.close();
 }
}
