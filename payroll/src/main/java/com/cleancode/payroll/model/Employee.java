package com.cleancode.payroll.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {
  private String name;
  private String dob;
  private String role;
  private String startdate;
  private int salary;
  public static final double HSL = 0.06;

  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getStartdate() {
    return startdate;
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public int getAge() {
    LocalDate birthday = LocalDate.parse(this.dob, dateFormatter);
    return Period.between(birthday, LocalDate.now()).getYears();
  }

  public int getWorkYears() {
    LocalDate startDate = LocalDate.parse(this.startdate, dateFormatter);
    return Period.between(startDate, LocalDate.now()).getYears();
  }

  public int getWorkMonths() {
    LocalDate startDate = LocalDate.parse(this.startdate, dateFormatter);
    return Period.between(startDate, LocalDate.now()).getMonths();
  }

  public int getCurrentSalary() {
    int workYears = getWorkYears();
    if (workYears == 1) {
      return salary;
    }
    for (int i = 1; i < workYears; i++) {
      salary *= (1 + HSL);
    }
    return salary;
  }

  @Override
  public String toString() {
    return "Employee [dob=" + dob + ", name=" + name + ", age in years = " + this.getAge() + ", work in "
        + this.getWorkYears() + " years, " + this.getWorkMonths() + " months, current salary = "
        + this.getCurrentSalary() + "]";
  }
}
