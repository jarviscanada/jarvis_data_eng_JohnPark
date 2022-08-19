package ca.jrvs.apps.trading.model.domain;

public class SecurityOrder implements Entity<Integer> {

  public enum Status {
      FILLED, CANCELLED
  }

  private Integer id;
  private Integer accountId;
  private String status;
  private String ticker;
  private Long size;
  private double price;
  private String notes;


  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(Status status) { this.status = String.valueOf(status);}

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }


}
