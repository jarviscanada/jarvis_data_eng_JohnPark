package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;

import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;
  private Tweet tweet;
  private String text;
  private Double lat;
  private Double lon;


  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    //set up dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    //pass dependency
    this.dao = new TwitterDao(httpHelper);

    String hashTag = "#abc";
    text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
    lat = 1d;
    lon = -1d;
    tweet = TweetUtil.buildTweet(text, lon, lat);
  }

  @Test
  public void createFindAndDelete() throws Exception {
    //Create
    System.out.println("test");
    System.out.println(JsonUtil.toPrettyJson(tweet));
    Tweet createResponse = dao.create(tweet);
    System.out.println("test2");
    System.out.println(JsonUtil.toPrettyJson(createResponse));
    testHelper(createResponse);

    String id = createResponse.getIdStr();

    //Find
    Tweet findByIdResponse = dao.findById(id);
    testHelper(findByIdResponse);

    //Delete or cleanup
    Tweet deleteByIdResponse = dao.deleteById(id);
    testHelper(deleteByIdResponse);
  }


  public void testHelper(Tweet response) {
    assertEquals(text, response.getText());

    assertNotNull(response.getId());
    assertNotNull(response.getCoordinates());

    assertEquals(2, response.getCoordinates().getCoordinates().size());
    assertEquals(lon, response.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, response.getCoordinates().getCoordinates().get(1));
    assertTrue("#abc".contains(response.getEntities().getHashtags().get(0).getText()));
  }


}