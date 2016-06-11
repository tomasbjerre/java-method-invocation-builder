package se.bjurr.jmib.testcases;

public class ProductionYear {
 private final Integer year;

 public ProductionYear(Integer year) {
  this.year = year;
 }

 public Integer getYear() {
  return this.year;
 }

 @Override
 public String toString() {
  return Integer.toString(this.year);
 }
}
