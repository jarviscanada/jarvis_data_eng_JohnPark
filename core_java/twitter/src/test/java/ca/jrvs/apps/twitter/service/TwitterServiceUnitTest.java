package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  String tweetJsonStr = "{\n"
      + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
      + "\"id\":1,\n"
      + "\"text\":\"Test\",\n"
      + "\"entities\" : {\n"
      + "    \"hashtags\":[],"
      + "    \"user_mentions\":[]"
      + "    },\n"
      + "\"coordinates\":{"
      + "    \"coordinates\": [50.0, 0.0],"
      + "    \"type\": \"point\""
      + "    },"
      + "\"retweet_count\":0,\n"
      + "\"favorite_count\":0,\n"
      + "\"favorited\":false,\n"
      + "\"retweeeted\":false\n"
      + "}";

  @Test
  public void postTweet() throws IOException {
    Tweet tweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    when(dao.create(any())).thenReturn(tweet);


    Tweet responseTweet = service.postTweet(tweet);
    assertNotNull(responseTweet);
    assertNotNull(responseTweet.getText());
    assertEquals(responseTweet, tweet);
    assertEquals( 50.0, (Object) responseTweet.getCoordinates().getCoordinates().get(0));
    assertEquals( 0.0, (Object) responseTweet.getCoordinates().getCoordinates().get(1));

  }

  @Test
  public void showTweet() throws IOException {
    Tweet tweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    when(dao.findById(any())).thenReturn(tweet);

    Tweet responseTweet = service.showTweet(tweet.getId().toString(), null);
    assertNotNull(responseTweet);
    assertNotNull(responseTweet.getText());
    assertEquals(responseTweet, tweet);
    assertEquals((Double) 50.0, responseTweet.getCoordinates().getCoordinates().get(0));
    assertEquals((Double) 0.0, responseTweet.getCoordinates().getCoordinates().get(1));


  }

  @Test
  public void deleteTweets() {
    when(dao.deleteById(any())).thenReturn(new Tweet());

    List<Tweet> responseTweets = service.deleteTweets(new String[]{"1", "2"});
    responseTweets.forEach(Assert::assertNotNull);
  }

  @Test
  public void showTweetValidArguments() throws IOException {
    Tweet tweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    when(dao.findById(any())).thenReturn(tweet);

    Tweet responseTweet = service.showTweet(tweet.getId().toString(),
        new String[]{"id", "text", "coordinates"});

    assertNotNull(responseTweet);
    assertEquals((Long) 1L, responseTweet.getId());
    assertEquals("Test", responseTweet.getText());
    assertEquals((Double) 50.0, responseTweet.getCoordinates().getCoordinates().get(0));
    assertEquals((Double) 0.0, responseTweet.getCoordinates().getCoordinates().get(1));
  }

  @Test
  public void showTweetInvalidArguments() throws IOException {
    Tweet tweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    when(dao.findById(any())).thenReturn(tweet);

    try {
      service.showTweet(tweet.getId().toString(), new String[]{"NOT", "VALID"});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}