package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  @Override
  public Tweet create(Tweet tweet) {
    URI uri;
    try {
      uri = getPostUri(tweet);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String s) {
    URI uri;
    try {
      uri = getShowUri(s);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid id: " + s, e);
    }
    HttpResponse response = httpHelper.httpGet(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String s) {
    URI uri;
    try {
      uri = getDeleteUri(s);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid id: " + s, e);
    }
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  private URI getPostUri(Tweet tweet) throws URISyntaxException {
    String uriStringFormat = API_BASE_URI + POST_PATH + QUERY_SYM;
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    uriStringFormat += "status" + EQUAL + percentEscaper.escape(tweet.getText());
    uriStringFormat += AMPERSAND + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(0);
    uriStringFormat += AMPERSAND + "long" + EQUAL + tweet.getCoordinates().getCoordinates().get(1);

    return new URI(uriStringFormat);
  }

  private URI getShowUri(String id) throws URISyntaxException {
    return new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
  }

  private URI getDeleteUri(String id) throws URISyntaxException {
    return new URI(API_BASE_URI + DELETE_PATH + id + ".json");
  }

  private Tweet parseResponseBody(HttpResponse response, int expectedStatusCode) {
    Tweet tweet;

    // Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    //Convert Response Entity to str
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    // Deserialize JSON string to Tweet object
    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON str to Object", e);
    }

    return tweet;
  }
}
