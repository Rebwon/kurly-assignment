package com.rebwon.kurly.coupon.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.rebwon.kurly.coupon.domain.exception.DiscountAmountLessThanZero;
import com.rebwon.kurly.coupon.domain.exception.DiscountRateGreatherThanOnehundredPercent;
import com.rebwon.kurly.coupon.domain.exception.DiscountRateLessThanZero;
import com.rebwon.kurly.coupon.domain.exception.MinOrderAmountLessThanZero;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.general.domain.Ratio;
import org.junit.jupiter.api.Test;

class CouponTest {

  @Test
  void when_AmountDiscountCoupon_Create_Than_MinOrderAmountLessThanZero() {
    assertThatThrownBy(
        () -> new AmountDiscountCoupon(Money.wons(-1000l), null)
    ).isInstanceOf(MinOrderAmountLessThanZero.class);
  }

  @Test
  void when_AmountDiscountCoupon_Create_Than_DiscountAmountLessThanZero() {
    assertThatThrownBy(
        () -> new AmountDiscountCoupon(Money.wons(1000l), Money.wons(-1000l))
    ).isInstanceOf(DiscountAmountLessThanZero.class);
  }

  @Test
  void when_PercentDiscountCoupon_Create_Than_DiscountRateLessThanZero() {
    assertThatThrownBy(
        () -> new PercentDiscountCoupon(Money.wons(1000l), Ratio.valueOf(-0.1))
    ).isInstanceOf(DiscountRateLessThanZero.class);
  }

  @Test
  void when_PercentDiscountCoupon_Create_Than_DiscountRateGreatherThanOnehundredPercent() {
    assertThatThrownBy(
        () -> new PercentDiscountCoupon(Money.wons(1000l), Ratio.valueOf(101))
    ).isInstanceOf(DiscountRateGreatherThanOnehundredPercent.class);
  }
}