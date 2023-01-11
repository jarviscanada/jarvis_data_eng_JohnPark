package ca.jrvs.apps.trading.model.domain;

public class Position {
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

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer account_id) {
    this.accountId = account_id;
  }

}
