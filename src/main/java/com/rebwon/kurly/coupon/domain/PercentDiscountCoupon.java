package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.general.domain.Ratio;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;

public class PercentDiscountCoupon extends Coupon {
  private Ratio ratio;

  public PercentDiscountCoupon(Money minOrderAmount, Ratio ratio) {
    super(minOrderAmount);
    this.ratio = ratio;
  }

  @Override
  public Money calculateDiscountAmount(List<Order> orders) {
    Money totalAmount = Money.sum(orders, Order::totalAmount);
    if(isSatisfiedBy(totalAmount)) {
      return Money.ZERO;
    }

    return this.ratio.of(totalAmount);
  }
}
