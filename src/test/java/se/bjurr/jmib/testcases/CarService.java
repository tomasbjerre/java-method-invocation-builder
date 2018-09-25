package se.bjurr.jmib.testcases;

import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_WITH_ON_METHOD;

import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder(style = SUPPLY_INSTANCE_WITH_ON_METHOD)
public class CarService {
  public CarService() {}

  public String getCarsByFilter( //
      @Default("Color.BLUE") Color color, //
      @Default("new ProductionYear(2001)") ProductionYear productionYear, //
      @Default("Tomas") String owner //
      ) {
    return "Filtering... " + color + productionYear + owner;
  }
}
