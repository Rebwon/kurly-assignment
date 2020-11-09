package com.rebwon.kurly.order.domain;

import com.rebwon.kurly.general.domain.Money;
import java.util.Objects;
import java.util.UUID;

public class Order {
  private UUID id;
  private int quantity;
  private Money amount;

  private Order(Money amount, int quantity) {
    this.id = UUID.randomUUID();
    this.quantity = quantity;
    this.amount = amount;
  }

  public static Order create(long amount, int quantity) {
    return new Order(Money.wons(amount), quantity);
  }

  public Money totalAmount() {
    return amount.times(quantity);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
