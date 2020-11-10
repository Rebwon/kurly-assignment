package com.rebwon.kurly.coupon.usecase;

import com.rebwon.kurly.common.usecase.UseCase;
import com.rebwon.kurly.general.domain.Money;
import java.util.Objects;

public class DiscountCouponUseCase implements UseCase<DiscountRequest, DiscountResponse> {

  @Override
  public DiscountResponse execute(DiscountRequest request) {
    Objects.requireNonNull(request);
    Money money = request.getCoupon().calculateDiscountAmount(request.getOrders());
    return money.equals(Money.ZERO) ? new DiscountResponse(false, Money.ZERO) : new DiscountResponse(true, money);
  }
}
