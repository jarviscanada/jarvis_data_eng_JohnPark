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
    "text",
    "indices"
})
@Generated("jsonschema2pojo")
public class Hashtag {

  @JsonProperty("text")
  private String text;

  @JsonProperty("indices")
  private List<Long> indices;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonGetter("text")
  public String getText() {
    return text;
  }

  @JsonSetter("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonGetter("indices")
  public List<Long> getIndices() {
    return indices;
  }

  @JsonSetter("indices")
  public void setIndices(List<Long> indices) {
    this.indices = indices;
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

