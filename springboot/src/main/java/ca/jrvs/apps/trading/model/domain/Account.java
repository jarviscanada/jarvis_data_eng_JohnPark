package ca.jrvs.apps.trading.model.domain;


public class Account implements Entity<Integer> {
  private Integer id;
  private Integer traderId;
  private double amount;


  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTraderId() {
    return traderId;
  }

  public void setTraderId(Integer traderId) {
    this.traderId = traderId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }


}
