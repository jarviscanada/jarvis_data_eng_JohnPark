package ca.jrvs.apps.twitter.model;

public class Tweet {

  private String createdAt;
  private Long id;
  private String idStr;
  private String text;
  private Entities entities;
  private Coordinates coordinates = null;
  private Long retweetCount;
  private Long favoriteCount;
  private Boolean favorited;
  private Boolean retweeted;


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdStr() {
    return idStr;
  }

  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Entities getEntities() {
    return entities;
  }

  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Long getRetweetCount() {
    return retweetCount;
  }

  public void setRetweetCount(Long retweetCount) {
    this.retweetCount = retweetCount;
  }

  public Long getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(Long favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  public Boolean getFavorited() {
    return favorited;
  }

  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  public Boolean getRetweeted() {
    return retweeted;
  }

  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }
}
