package ca.jrvs.apps.trading.model.domain;

public class Position implements Entity<Integer> {
  private Integer id;
  private Integer accountId;
  private String ticker;
  private Long position;


  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Long getPosition() {
    return position;
  }

  public void setPosition(Long position) {
    this.position = position;
  }

  public Integer getAccount_id() {
    return accountId;
  }

  public void setAccount_id(Integer account_id) {
    this.accountId = account_id;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }
}
