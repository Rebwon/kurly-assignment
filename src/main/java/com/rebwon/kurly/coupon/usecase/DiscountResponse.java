package com.rebwon.kurly.coupon.usecase;

import com.rebwon.kurly.common.usecase.UseCaseResponse;
import com.rebwon.kurly.general.domain.Money;

public class DiscountResponse implements UseCaseResponse {
  private boolean usability;
  private Money discountAmount;

  public DiscountResponse(boolean usability,
      Money discountAmount) {
    this.usability = usability;
    this.discountAmount = discountAmount;
  }

  public boolean isUsability() {
    return usability;
  }

  public Money getDiscountAmount() {
    return discountAmount;
  }
}
