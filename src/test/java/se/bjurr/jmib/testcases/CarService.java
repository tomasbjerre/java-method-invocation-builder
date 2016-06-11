package se.bjurr.jmib.testcases;

import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder
public class CarService {
 public CarService() {
 }

 public String getCarsByFilter(//
   @Default("Color.BLUE") Color color, //
   @Default("new ProductionYear(2001)") ProductionYear productionYear,//
   @Default("Tomas") String owner//
 ) {
  return "Filtering... " + color + productionYear + owner;
 }
}
