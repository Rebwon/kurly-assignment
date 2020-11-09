package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;

public class AmountDiscountCoupon extends Coupon {
  private Money discountAmount;

  public AmountDiscountCoupon(Money minOrderAmount, Money discountAmount) {
    super(minOrderAmount);
    this.discountAmount = discountAmount;
  }

  @Override
  public Money calculateDiscountAmount(List<Order> orders) {
    if(isSatisfiedBy(Money.sum(orders, Order::totalAmount))) {
      return Money.ZERO;
    }
    return discountAmount;
  }
}
