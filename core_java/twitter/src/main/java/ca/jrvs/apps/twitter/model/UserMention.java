package ca.jrvs.apps.twitter.model;


import java.util.List;

public class UserMention {

  private String name;
  private List<Long> indices;
  private String screenName;
  private Long id;
  private String id_str;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getIndices() {
    return indices;
  }

  public void setIndices(List<Long> indices) {
    this.indices = indices;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getId_str() {
    return id_str;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }
}
