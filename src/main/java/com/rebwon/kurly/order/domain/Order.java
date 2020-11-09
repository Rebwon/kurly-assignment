package com.rebwon.kurly.order.domain;

import com.rebwon.kurly.general.domain.Money;

public class Order {
  private int quantity;
  private Money amount;

  private Order(Money amount, int quantity) {
    this.quantity = quantity;
    this.amount = amount;
  }

  public static Order create(long amount, int quantity) {
    return new Order(Money.wons(amount), quantity);
  }

  public Money totalAmount() {
    return amount.times(quantity);
  }
}
