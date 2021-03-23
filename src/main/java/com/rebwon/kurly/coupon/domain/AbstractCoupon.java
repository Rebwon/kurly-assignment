package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.coupon.domain.exception.MinOrderAmountLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCoupon {
  private Money minOrderAmount;

  protected AbstractCoupon(Money minOrderAmount) {
    Objects.requireNonNull(minOrderAmount);
    this.minOrderAmount = validate(minOrderAmount);
  }

  private Money validate(Money minOrderAmount) {
    if(minOrderAmount.isLessThan(Money.ZERO))
      throw new MinOrderAmountLessThanZero();
    return minOrderAmount;
  }

  private boolean isLessThanMinOrderAmount(Money other) {
    return other.isLessThan(this.minOrderAmount);
  }

  public Money calculateDiscountAmount(List<Order> orders) {
    Money totalAmount = Money.sum(orders, Order::totalAmount);
    if(isLessThanMinOrderAmount(totalAmount)) {
      return Money.ZERO;
    }
    return getDiscountAmount(totalAmount);
  }

  protected abstract Money getDiscountAmount(Money totalAmount);
}
