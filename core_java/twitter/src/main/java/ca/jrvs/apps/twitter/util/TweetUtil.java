package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.UserMention;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetUtil {

  public static Tweet buildTweet(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    tweet.setText(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(Arrays.asList(lon, lat));
    tweet.setCoordinates(coordinates);
    return tweet;
  }

  public static void validatePostTweet(Tweet tweet) {
    // Valid tweet text length
    if (tweet.getText().length() > 140) {
      throw new IllegalArgumentException("Text length too large, limit to 140 char");
    }

    // Valid longitude and latitude range
    List<Double> coordinates = tweet.getCoordinates().getCoordinates();
    Double longitude = coordinates.get(0);
    Double latitude = coordinates.get(1);
    if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
      throw new IllegalArgumentException(
          "longitude and latitude out of range, possible ranges: -90 <= lat <= 90 and -180 <= long <= 180");
    }
  }

  public static void validateTweetId(String id) {
    try {
      Long.parseLong(id);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid tweet id, either nonnumerical included in the String or out of range");
    }
  }
}