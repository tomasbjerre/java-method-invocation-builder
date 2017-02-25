package se.bjurr.jmib.testcases;

import java.lang.String;

public final class CarServiceGetCarsByFilterBuilder {
  private Color color;

  private ProductionYear productionYear;

  private String owner;

  private CarService instance;

  private CarServiceGetCarsByFilterBuilder() {
    this.color = Color.BLUE;
    this.productionYear = new ProductionYear(2001);
    this.owner = "Tomas";
  }

  public CarServiceGetCarsByFilterBuilder withColor(final Color color) {
    this.color = color;
    return this;
  }

  public CarServiceGetCarsByFilterBuilder withProductionYear(final ProductionYear productionYear) {
    this.productionYear = productionYear;
    return this;
  }

  public CarServiceGetCarsByFilterBuilder withOwner(final String owner) {
    this.owner = owner;
    return this;
  }

  public CarServiceGetCarsByFilterBuilder on(final CarService instance) {
    this.instance = instance;
    return this;
  }

  public static CarServiceGetCarsByFilterBuilder getCarsByFilter() {
    return new CarServiceGetCarsByFilterBuilder();
  }

  public String invoke() {
    if (this.instance == null) {
      throw new java.lang.IllegalStateException("You must supply an instance to the builder!");
    }
    return this.instance.getCarsByFilter(color,productionYear,owner);
  }
}
