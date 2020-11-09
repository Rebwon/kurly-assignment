package com.rebwon.kurly.coupon.usecase;

import com.rebwon.kurly.common.usecase.UseCaseRequest;
import com.rebwon.kurly.coupon.domain.Coupon;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;

public class DiscountRequest implements UseCaseRequest {
  private List<Order> orders;
  private Coupon coupon;

  public DiscountRequest(List<Order> orders, Coupon coupon) {
    this.orders = orders;
    this.coupon = coupon;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public Coupon getCoupon() {
    return coupon;
  }
}
