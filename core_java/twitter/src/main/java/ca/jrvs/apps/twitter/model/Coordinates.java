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
    "coordinates",
    "type"
})
@Generated("jsonschema2pojo")
public class Coordinates {

  @JsonProperty("coordinates")
  private List<Double> coordinates;

  @JsonProperty("coordinates")
  private String type;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonGetter("coordinates")
  public List<Double> getCoordinates() {
    return coordinates;
  }

  @JsonSetter("coordinates")
  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  @JsonGetter("type")
  public String getType() {
    return type;
  }

  @JsonSetter("type")
  public void setType(String type) {
    this.type = type;
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
