package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterService twitterService;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    TwitterDao twitterDao = new TwitterDao(httpHelper);
    twitterService = new TwitterService(twitterDao);

  }

  @Test
  public void createFindAndDeleteV1() {
    String text = "test";
    Double lon = 35.0;
    Double lat = 30.0;

    Tweet tweet = TweetUtil.buildTweet(text, lon, lat);

    // Test Create - postTweet
    Tweet postTweetResponse = twitterService.postTweet(tweet);
    testHelper(postTweetResponse, text, lon, lat);

    // Test Find - showTweet
    String id = postTweetResponse.getIdStr();
    Tweet showTweetResponse = twitterService.showTweet(id, null);
    testHelper(showTweetResponse, text, lon, lat);

    // Test Delete - deleteTweets
    List<Tweet> deleteTweetsResponses = twitterService.deleteTweets(new String[]{id});
    for (Tweet response : deleteTweetsResponses) {
      testHelper(response, text, lon, lat);
    }

  }

  @Test
  public void createFindAndDeleteV2() {
    List<String> texts = Arrays.asList("test1", "test2");
    List<Double> lons = Arrays.asList(42.1, 41.1);
    List<Double> lats = Arrays.asList(23.1, 23.4);
    String[] ids = new String[2];

    for (int i = 0; i < 2; i++) {
      Tweet tweet = TweetUtil.buildTweet(texts.get(i), lons.get(i), lats.get(i));

      // Test Create - postTweet
      Tweet postTweetResponse = twitterService.postTweet(tweet);
      testHelper(postTweetResponse, texts.get(i), lons.get(i), lats.get(i));

      // Test Find - showTweet
      String id = postTweetResponse.getIdStr();
      Tweet showTweetResponse = twitterService.showTweet(id, null);
      testHelper(showTweetResponse, texts.get(i), lons.get(i), lats.get(i));

      ids[i] = id;
    }

    List<Tweet> deleteTweetsResponses = twitterService.deleteTweets(ids);
    for (int i = 0; i < 2; i++) {
      testHelper(deleteTweetsResponses.get(i), texts.get(i), lons.get(i), lats.get(i));
    }

  }

  public void testHelper(Tweet response, String text, Double lon, Double lat) {
    assertEquals(text, response.getText());

    assertNotNull(response.getId());
    assertNotNull(response.getCoordinates());

    assertEquals(2, response.getCoordinates().getCoordinates().size());
    assertEquals(lon, response.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, response.getCoordinates().getCoordinates().get(1));
  }
}