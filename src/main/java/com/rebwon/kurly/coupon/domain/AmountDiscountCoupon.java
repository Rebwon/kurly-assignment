package com.rebwon.kurly.coupon.domain;

import com.rebwon.kurly.coupon.domain.exception.DiscountAmountLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;
import java.util.Objects;

public class AmountDiscountCoupon extends AbstractCoupon {
  private Money discountAmount;

  public AmountDiscountCoupon(Money minOrderAmount, Money discountAmount) {
    super(minOrderAmount);
    Objects.requireNonNull(discountAmount);
    this.discountAmount = validate(discountAmount);
  }

  private Money validate(Money discountAmount) {
    if(discountAmount.isLessThan(Money.ZERO))
      throw new DiscountAmountLessThanZero();
    return discountAmount;
  }

  @Override
  protected Money getDiscountAmount(Money totalAmount) {
    return discountAmount;
  }
}
