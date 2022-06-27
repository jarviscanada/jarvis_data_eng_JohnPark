package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})
@Generated("jsonschema2pojo")
public class Tweet {

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("id_str")
  private String idStr;

  @JsonProperty("text")
  private String text;

  @JsonProperty("entities")
  private Entities entities;

  @JsonProperty("coordinates")
  private Coordinates coordinates = null;

  @JsonProperty("retweet_count")
  private Long retweetCount;

  @JsonProperty("favorite_count")
  private Long favoriteCount;

  @JsonProperty("favorited")
  private Boolean favorited;

  @JsonProperty("retweeted")
  private Boolean retweeted;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @JsonGetter("created_at")
  public String getCreatedAt() {
    return createdAt;
  }

  @JsonSetter("created_at")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonGetter("id")
  public Long getId() {
    return id;
  }

  @JsonSetter("id")
  public void setId(Long id) {
    this.id = id;
  }

  @JsonGetter("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonSetter("id_str")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  @JsonGetter("text")
  public String getText() {
    return text;
  }

  @JsonSetter("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonGetter("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonSetter("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  @JsonGetter("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonSetter("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonGetter("retweet_count")
  public Long getRetweetCount() {
    return retweetCount;
  }

  @JsonSetter("retweet_count")
  public void setRetweetCount(Long retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonGetter("favorite_count")
  public Long getFavoriteCount() {
    return favoriteCount;
  }

  @JsonSetter("favorite_count")
  public void setFavoriteCount(Long favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @JsonGetter("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  @JsonSetter("favorited")
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  @JsonGetter("retweeted")
  public Boolean getRetweeted() {
    return retweeted;
  }

  @JsonSetter("retweeted")
  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }
}
