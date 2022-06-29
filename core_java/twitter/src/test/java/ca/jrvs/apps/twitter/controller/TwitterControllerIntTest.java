package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private TwitterController controller;

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
    Service service = new TwitterService(twitterDao);
    controller = new TwitterController(service);
  }


  @Test
  public void createFindAndDelete() throws Exception {
    //Test postTweet
    String[] args1 = new String[]{"post", "Controller integration testing 1", "32.0:48.0"};
    String[] args2 = new String[]{"post", "Controller integration testing 2", "37.0:59.0"};
    Tweet response1 = controller.postTweet(args1);
    Tweet response2 = controller.postTweet(args2);
    testHelper(response1, "Controller integration testing 1", 32.0, 48.0);
    testHelper(response2, "Controller integration testing 2", 37.0, 59.0);

    String id1 = response1.getIdStr();
    String id2 = response2.getIdStr();

    //Test showTweet
    args1 = new String[]{"show", id1};
    args2 = new String[]{"show", id2};
    response1 = controller.showTweet(args1);
    response2 = controller.showTweet(args2);
    testHelper(response1, "Controller integration testing 1", (Double) 32.0, (Double) 48.0);
    testHelper(response2, "Controller integration testing 2", (Double) 37.0, (Double) 59.0);

    //Test deleteTweet
    String[] args = new String[]{"delete", id1, id2};
    List<Tweet> responses = controller.deleteTweet(args);
    for (Tweet response : responses) {
      if (response.getIdStr().equals(id1)) {
        testHelper(response1, "Controller integration testing 1", (Double) 32.0, (Double) 48.0);
      } else if (response.getIdStr().equals(id2)) {
        testHelper(response2, "Controller integration testing 2", (Double) 37.0, (Double) 59.0);
      }
      // This is error situation
      else {
        fail();
      }
    }
  }

  private void testHelper(Tweet response, String expText, Double expLat, Double expLong) {
    assertNotNull(response);

    Double longitude = response.getCoordinates().getCoordinates().get(0);
    Double latitude = response.getCoordinates().getCoordinates().get(1);

    assertNotNull(response.getId());
    assertEquals(expText, response.getText());
    assertEquals(expLat, latitude);
    assertEquals(expLong, longitude);
  }
}