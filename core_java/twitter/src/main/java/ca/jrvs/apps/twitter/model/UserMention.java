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
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "indices",
    "screen_name",
    "id",
    "id_str"
})
@Generated("jsonschema2pojo")
public class UserMention {

  @JsonProperty("name")
  private String name;

  @JsonProperty("indices")
  private List<Long> indices;

  @JsonProperty("screen_name")
  private String screenName;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("id_str")
  private String idStr;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonGetter("name")
  public String getName() {
    return name;
  }

  @JsonSetter("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonGetter("indices")
  public List<Long> getIndices() {
    return indices;
  }

  @JsonSetter("indices")
  public void setIndices(List<Long> indices) {
    this.indices = indices;
  }

  @JsonGetter("screen_name")
  public String getScreenName() {
    return screenName;
  }

  @JsonSetter("screen_name")
  public void setScreenName(String screenName) {
    this.screenName = screenName;
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
  public void setIdStr(String id_str) {
    this.idStr = id_str;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
