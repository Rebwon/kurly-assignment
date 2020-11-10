package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.coupon.domain.exception.MinOrderAmountLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;
import java.util.Objects;

public abstract class Coupon {
  private Money minOrderAmount;

  protected Coupon(Money minOrderAmount) {
    Objects.requireNonNull(minOrderAmount);
    this.minOrderAmount = validate(minOrderAmount);
  }

  private Money validate(Money minOrderAmount) {
    if(minOrderAmount.isLessThan(Money.ZERO))
      throw new MinOrderAmountLessThanZero();
    return minOrderAmount;
  }

  protected boolean isSatisfiedBy(Money other) {
    return other.isLessThan(this.minOrderAmount);
  }

  public abstract Money calculateDiscountAmount(List<Order> orders);
}
