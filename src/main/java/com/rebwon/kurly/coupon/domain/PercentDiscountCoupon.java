package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.coupon.domain.exception.DiscountRateGreatherThanOnehundredPercent;
import com.rebwon.kurly.coupon.domain.exception.DiscountRateLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.general.domain.Ratio;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;

public class PercentDiscountCoupon extends Coupon {
  private Ratio ratio;

  public PercentDiscountCoupon(Money minOrderAmount, Ratio ratio) {
    super(minOrderAmount);
    assert ratio != null;
    this.ratio = validate(ratio);
  }

  private Ratio validate(Ratio ratio) {
    if(ratio.getRate() < 0)
      throw new DiscountRateLessThanZero();
    if(ratio.getRate() > 100)
      throw new DiscountRateGreatherThanOnehundredPercent();
    return ratio;
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
