package com.rebwon.kurly.general.domain;

public class Ratio {
  private double rate;

  public static Ratio valueOf(double rate) {
    return new Ratio(rate);
  }

  Ratio(double rate) {
    this.rate = rate;
  }

  public Money of(Money price) {
    return price.times(rate);
  }

  public double getRate() {
    return rate;
  }
}
