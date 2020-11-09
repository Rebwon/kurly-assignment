package com.rebwon.kurly.general.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class Money {
  public static final Money ZERO = Money.wons(0);

  private final BigDecimal amount;

  public static Money wons(long amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  public static Money wons(double amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary) {
    return bags.stream().map(bag -> monetary.apply(bag)).reduce(Money.ZERO, Money::plus);
  }

  public Money plus(Money amount) {
    return new Money(this.amount.add(amount.amount));
  }

  public Money times(double percent) {
    return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
  }

  Money(BigDecimal amount) {
    this.amount = amount;
  }

  public boolean isLessThan(Money other) {
    return this.amount.compareTo(other.amount) < 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(amount.doubleValue(), money.amount.doubleValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }
}
