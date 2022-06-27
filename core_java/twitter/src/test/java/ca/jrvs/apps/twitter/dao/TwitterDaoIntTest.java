package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {
  private TwitterDao dao;
  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    //set up dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    //pass dependency
    this.dao = new TwitterDao(httpHelper);
  }

//  @After
//  public void tearDown() {
//    assertNotNull(id);
//    Tweet response = dao.deleteById(id.toString());
//
//    // Test that deleting a tweet worked
//    testResponse(response);
//  }

  @Test
  public void create() throws Exception {
    String hashTag = "#abc";
    String text = "@someone sometext" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet));

    Tweet tweet = dao.create(postTweet);

    testHelper(text, tweet, lon, lat);

    Tweet toDeleteTweet = dao.deleteById(tweet.getId().toString());

    testHelper(text, toDeleteTweet, lon, lat);
  }

  @Test
  public void findById() throws Exception {
    String hashTag = "#abc";
    String text = "@someone sometext" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet));

    Tweet response = dao.create(postTweet);

    Long id = response.getId();
    response = dao.findById(id.toString());

    testHelper(text, response, lon, lat);

    Tweet toDeleteTweet = dao.deleteById(response.getId().toString());

    testHelper(text, toDeleteTweet, lon, lat);

  }

  public void testHelper(String text, Tweet tweet, Double lon, Double lat) {
    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getId());
    assertNotNull(tweet.getCoordinates());

    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
  }


}