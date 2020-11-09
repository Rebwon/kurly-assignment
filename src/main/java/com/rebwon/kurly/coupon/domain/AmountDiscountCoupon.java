package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.coupon.domain.exception.DiscountAmountLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;

public class AmountDiscountCoupon extends Coupon {
  private Money discountAmount;

  public AmountDiscountCoupon(Money minOrderAmount, Money discountAmount) {
    super(minOrderAmount);
    assert discountAmount != null;
    this.discountAmount = validate(discountAmount);
  }

  private Money validate(Money discountAmount) {
    if(discountAmount.isLessThan(Money.ZERO))
      throw new DiscountAmountLessThanZero();
    return discountAmount;
  }

  @Override
  public Money calculateDiscountAmount(List<Order> orders) {
    Money totalAmount = Money.sum(orders, Order::totalAmount);
    if(isSatisfiedBy(totalAmount)) {
      return Money.ZERO;
    }
    return discountAmount;
  }
}
