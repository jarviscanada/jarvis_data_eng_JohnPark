package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.io.IOException;
import java.util.Optional;
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
    assertEquals( 50.0, (Object) responseTweet.getCoordinates().getCoordinates().get(0));
    assertEquals( 0.0, (Object) responseTweet.getCoordinates().getCoordinates().get(1));

    Tweet responseTweetV2 = service.showTweet(tweet.getId().toString(), new String[]{"id"});
    assertNotNull(responseTweetV2);
    assertEquals(1L, (Object) responseTweetV2.getId());
  }

  @Test
  public void deleteTweets() {
  }
}