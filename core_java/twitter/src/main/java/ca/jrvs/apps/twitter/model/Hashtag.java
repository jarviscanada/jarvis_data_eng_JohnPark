package ca.jrvs.apps.twitter.model;

import java.util.List;


public class Hashtag {

  private String text;
  private List<Long> indices;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<Long> getIndices() {
    return indices;
  }

  public void setIndices(List<Long> indices) {
    this.indices = indices;
  }
}

