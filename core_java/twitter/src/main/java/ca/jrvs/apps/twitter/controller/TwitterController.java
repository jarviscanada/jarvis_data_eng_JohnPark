package ca.jrvs.apps.twitter.controller;

import static ca.jrvs.apps.twitter.util.TweetUtil.buildTweet;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3 || !args[0].equals("post")) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    String tweet_txt = args[1];
    String coord = args[2];
    String[] coordArray = coord.split(COORD_SEP);
    if (coordArray.length != 2 || StringUtils.isEmpty(tweet_txt)) {
      throw new IllegalArgumentException(
          "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }
    Double lat = null;
    Double lon = null;
    try {
      lat = Double.parseDouble(coordArray[0]);
      lon = Double.parseDouble(coordArray[1]);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" latitude:longitude",
          e);
    }

    Tweet postTweet = buildTweet(tweet_txt, lon, lat);
    return service.postTweet(postTweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 2 || !args[0].equals("show")) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp show tweet_id [fields]");
    } else if (args.length == 2) {
      return service.showTweet(args[1], null);
    } else {
      return service.showTweet(args[1], Arrays.copyOfRange(args, 2, args.length));
    }
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if(args.length < 2 || !args[0].equals("delete")) {
      throw new RuntimeException("USAGE: TwitterCLIApp delete [one id or more ids]");
    }

    return service.deleteTweets(Arrays.copyOfRange(args, 1, args.length));
  }
}
