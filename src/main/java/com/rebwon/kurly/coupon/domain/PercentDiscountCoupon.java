package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.coupon.domain.exception.DiscountRateGreatherThanOnehundredPercent;
import com.rebwon.kurly.coupon.domain.exception.DiscountRateLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.general.domain.Ratio;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;
import java.util.Objects;

public class PercentDiscountCoupon extends AbstractCoupon {
  private Ratio ratio;

  public PercentDiscountCoupon(Money minOrderAmount, Ratio ratio) {
    super(minOrderAmount);
    Objects.requireNonNull(ratio);
    this.ratio = validate(ratio);
  }

  private Ratio validate(Ratio ratio) {
    if(ratio.getRate() < 0)
      throw new DiscountRateLessThanZero();
    if(ratio.getRate() > 1)
      throw new DiscountRateGreatherThanOnehundredPercent();
    return ratio;
  }

  @Override
  protected Money getDiscountAmount(Money totalAmount) {
    return this.ratio.of(totalAmount);
  }
}
