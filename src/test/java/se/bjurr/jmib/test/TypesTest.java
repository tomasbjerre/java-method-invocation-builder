package se.bjurr.jmib.test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import se.bjurr.jmib.testcases.AClass;
import se.bjurr.jmib.testcases.AnInterface;
import se.bjurr.jmib.testcases.ClassWithConstructor;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class TypesTest {
 private static final String EXPECTED_DIR = "src/test/expected/se/bjurr/jmib/testcases/";
 private static final String TESTCASES_DIR = "src/test/java/se/bjurr/jmib/testcases/";

 @SuppressWarnings("unchecked")
 @BeforeClass
 public static void generate() throws IOException {
  Iterable<? extends File> filesToCompile = findAllJavaFiles(new File(TESTCASES_DIR));
  List<Class<?>> filesToProcess = newArrayList(AClass.class, AnInterface.class, ClassWithConstructor.class);
  AnnotationProcessingTestUtil.processClasses(filesToCompile, filesToProcess);
 }

 private static List<File> findAllJavaFiles(File file) {
  List<File> found = newArrayList();
  File[] children = file.listFiles();
  if (children != null) {
   for (File child : children) {
    if (child.isFile() && child.getAbsolutePath().endsWith(".java")) {
     found.add(new File(child.getAbsolutePath()));
    } else {
     found.addAll(findAllJavaFiles(child));
    }
   }
  }
  return found;
 }

 @Test
 public void test() throws IOException {
  List<File> expectedResults = findAllJavaFiles(new File(EXPECTED_DIR));
  for (File expeFile : expectedResults) {
   String expectedContent = Files.toString(expeFile, Charsets.UTF_8);
   String actualFile = expeFile.getAbsolutePath().replaceAll("expected", "generated");
   String actualContent = Files.toString(new File(actualFile), Charsets.UTF_8);

   String test = expeFile.getAbsolutePath() + " = " + actualFile;
   assertThat(actualContent)//
     .as(test)//
     .isEqualTo(expectedContent);
  }
 }
}
