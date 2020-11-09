package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Coupon {
  private UUID generalNumber;
  protected Money minOrderAmount;

  public Coupon(Money minOrderAmount) {
    this.generalNumber = UUID.randomUUID();
    this.minOrderAmount = minOrderAmount;
  }

  protected boolean isSatisfiedBy(Money other) {
    return other.isLessThan(this.minOrderAmount);
  }

  public abstract Money calculateDiscountAmount(List<Order> orders);

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coupon coupon = (Coupon) o;
    return Objects.equals(generalNumber, coupon.generalNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(generalNumber);
  }
}
