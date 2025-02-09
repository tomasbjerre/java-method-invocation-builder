package se.bjurr.jmib.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import se.bjurr.jmib.testcases.AClass;
import se.bjurr.jmib.testcases.AnInterface;
import se.bjurr.jmib.testcases.ClassWithConstructor;

public class TypesTest {
  private static final String GENERATED_DIR = "src/test/generated/se/bjurr/jmib/testcases/";
  private static final String TESTCASES_DIR = "src/test/java/se/bjurr/jmib/testcases/";

  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void generate() throws IOException {
    Iterable<? extends File> filesToCompile = findAllJavaFiles(new File(TESTCASES_DIR));
    List<Class<?>> filesToProcess =
        Arrays.asList(AClass.class, AnInterface.class, ClassWithConstructor.class);
    AnnotationProcessingTestUtil.processClasses(filesToCompile, filesToProcess);
  }

  private static List<File> findAllJavaFiles(File file) {
    List<File> found = new ArrayList<>();
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
    List<File> actualResults = findAllJavaFiles(new File(GENERATED_DIR));
    for (File actualFile : actualResults) {
      String expectedFilePath = actualFile.getAbsolutePath().replaceAll("generated", "expected");
      File expectedFile = new File(expectedFilePath);
      String actualContent =
          new String(Files.readAllBytes(actualFile.toPath()), StandardCharsets.UTF_8);

      if (!expectedFile.exists()) {
        System.out.println(expectedFile.getAbsolutePath());
        Files.write(expectedFile.toPath(), actualContent.getBytes(StandardCharsets.UTF_8));
        continue;
      }

      String expectedContent =
          new String(Files.readAllBytes(expectedFile.toPath()), StandardCharsets.UTF_8);
      String test = expectedFile.getAbsolutePath() + " = " + actualFile;
      assertThat(actualContent) //
          .as(test) //
          .isEqualToIgnoringWhitespace(expectedContent);
    }
  }
}
