package com.rebwon.kurly.coupon.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.rebwon.kurly.common.UseCase;
import com.rebwon.kurly.coupon.domain.AmountDiscountCoupon;
import com.rebwon.kurly.coupon.domain.PercentDiscountCoupon;
import com.rebwon.kurly.general.domain.Money;
import com.rebwon.kurly.general.domain.Ratio;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscountCouponUseCaseTest {
  private UseCase<DiscountRequest, DiscountResponse> usecase;
  private DiscountRequest amountDiscountRequest;
  private DiscountRequest badAmountDiscountRequest;
  private DiscountRequest percentDiscountRequest;
  private DiscountRequest badPercentDiscountRequest;

  @BeforeEach
  void setUp() {
    usecase = new DiscountCouponUseCase();
    amountDiscountRequest = new DiscountRequest(
        List.of(Order.create(2000L, 2), Order.create(1000L, 1)),
        new AmountDiscountCoupon(Money.wons(5000L), Money.wons(1000L)));
    badAmountDiscountRequest = new DiscountRequest(
        List.of(Order.create(1000L, 1)),
        new AmountDiscountCoupon(Money.wons(5000L), Money.wons(1000L)));
    percentDiscountRequest = new DiscountRequest(
        List.of(Order.create(2000L, 2), Order.create(1000L, 1)),
        new PercentDiscountCoupon(Money.wons(5000L), Ratio.valueOf(0.1)));
    badPercentDiscountRequest = new DiscountRequest(
        List.of(Order.create(1000L, 1)),
        new PercentDiscountCoupon(Money.wons(5000L), Ratio.valueOf(0.1)));
  }

  @Test
  void given_AmountDiscountRequest_when_DiscountUseCase_Execute_Return_DiscountResponse() {
    // Act
    DiscountResponse response = usecase.execute(amountDiscountRequest);

    // Assert
    assertThat(response.getDiscountAmount()).isEqualTo(Money.wons(1000L));
    assertThat(response.isUsability()).isTrue();
  }

  @Test
  void given_BadAmountDiscountRequest_When_UseCase_Execute_Return_NotUsability() {
    // Act
    DiscountResponse response = usecase.execute(badAmountDiscountRequest);

    // Assert
    assertThat(response.getDiscountAmount()).isEqualTo(Money.ZERO);
    assertThat(response.isUsability()).isFalse();
  }

  @Test
  void given_PercentDiscountRequest_When_UseCase_Execute_Return_DiscountResponse() {
    // Act
    DiscountResponse response = usecase.execute(percentDiscountRequest);

    // Assert
    assertThat(response.isUsability()).isTrue();
    assertThat(response.getDiscountAmount()).isEqualTo(Money.wons(500L));
  }

  @Test
  void given_BadPercentDiscountRequest_When_UseCase_Execute_Return_NotUsability() {
    // Act
    DiscountResponse response = usecase.execute(badPercentDiscountRequest);

    // Assert
    assertThat(response.getDiscountAmount()).isEqualTo(Money.ZERO);
    assertThat(response.isUsability()).isFalse();
  }
}