package se.bjurr.jmib;

import static org.mockito.Mockito.mock;
import static se.bjurr.jmib.maven.AClassCustomTypeBuilder.customType;
import static se.bjurr.jmib.maven.AClassMethodWithVoidBuilder.methodWithVoid;
import static se.bjurr.jmib.maven.AnInterfaceDefaultValuesBuilder.defaultValues;
import static se.bjurr.jmib.maven.CarServiceGetCarsByFilterBuilder.getCarsByFilter;

import java.io.IOException;
import org.junit.Test;
import se.bjurr.jmib.maven.AClass;
import se.bjurr.jmib.maven.AnInterface;
import se.bjurr.jmib.maven.CarService;
import se.bjurr.jmib.maven.CustomType;

public class ExamplesTest {

  @Test
  public void withDefaultStyle() throws IOException {
    AClass instance = new AClass();
    CustomType customType = new CustomType(2);

    customType(instance) //
        .withCustomType(customType) //
        .invoke();

    customType(instance) //
        .withCustomType(customType) //
        .invoke();

    methodWithVoid(instance) //
        .invoke();
  }

  @Test
  public void withOnMethodStyle() throws IOException {
    CarService instance = new CarService();
    getCarsByFilter() //
        .on(instance) //
        .invoke();
  }

  @Test
  public void withParameterMethodStyle() throws IOException {
    AnInterface instance = mock(AnInterface.class);
    defaultValues() //
        .withProjectKey("projectKey") //
        .invoke(instance);
  }
}
