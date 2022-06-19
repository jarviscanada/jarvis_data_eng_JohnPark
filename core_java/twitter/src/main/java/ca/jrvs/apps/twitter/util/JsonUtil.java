package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtil {
  public static <T> T toObjectFromJson(String JsonStr, Class<T> clazz) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(JsonStr, clazz);
  }
}
