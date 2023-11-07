package edu.cmu.cs.analysis;

public class Currency {

  private final double amount;
  private final String type;

  public Currency(double amount, String type) {
    this.amount = amount;
    this.type = type;
  }

  public Currency toEuros(ExchangeRate exchangeRate) {
    double exchangeAmount = this.amount * exchangeRate.getRate("USD", "EUR");

    return new Currency(exchangeAmount, "EUR");
  }

  /**
   * Solution
   */
  public Currency toDollars(ExchangeRate exchangeRate) {
    double exchangeAmount = this.amount * exchangeRate.getRate("EUR", "USD");

    return new Currency(exchangeAmount, "USD");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Currency currency = (Currency) o;

    if (Double.compare(currency.amount, amount) != 0) {
      return false;
    }
    return type.equals(currency.type);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(amount);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + type.hashCode();
    return result;
  }

  public String toString() {
    return amount + " " + type;
  }
}
