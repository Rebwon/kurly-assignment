package com.rebwon.kurly.coupon.usecase;

import com.rebwon.kurly.common.usecase.UseCaseRequest;
import com.rebwon.kurly.coupon.domain.AbstractCoupon;
import com.rebwon.kurly.order.domain.Order;
import java.util.List;

public class DiscountRequest implements UseCaseRequest {
  private List<Order> orders;
  private AbstractCoupon abstractCoupon;

  public DiscountRequest(List<Order> orders, AbstractCoupon abstractCoupon) {
    this.orders = orders;
    this.abstractCoupon = abstractCoupon;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public AbstractCoupon getCoupon() {
    return abstractCoupon;
  }
}
