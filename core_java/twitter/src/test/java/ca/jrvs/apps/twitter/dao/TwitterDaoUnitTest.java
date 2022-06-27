package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;


  // Test correct JSON

  @Test
  public void create() throws Exception {
    // Test failed request
    String hashTag = "#abc";
    String text = "@someone sometext" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;

    //Exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));

    Tweet tweet = new Tweet();
    tweet.setText(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new ArrayList<Double>() {{
      add(lon);
      add(lat);
    }});
    tweet.setCoordinates(coordinates);

    try {
      dao.create(tweet);
      fail();
    } catch(RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "\"id\":1097607853932564480,\n"
        + "\"text\":\"Test\",\n"
        + "\"entities\" : {\n"
        + "    \"hashtags\":[],"
        + "    \"user_mentions\":[]"
        + "    },\n"
        + "\"coordinates\":null,\n"
        + "\"retweet_count\":0,\n"
        + "\"favorite_count\":0,\n"
        + "\"favorited\":false,\n"
        + "\"retweeeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    // Mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    tweet = spyDao.create(tweet);
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
    assertEquals(tweet, expectedTweet);

  }

  @Test
  public void findById() throws IOException {
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));

    try {
      dao.findById("1");
      fail();
    } catch(RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "\"id\":1,\n"
        + "\"text\":\"Test\",\n"
        + "\"entities\" : {\n"
        + "    \"hashtags\":[],"
        + "    \"user_mentions\":[]"
        + "    },\n"
        + "\"coordinates\":null,\n"
        + "\"retweet_count\":0,\n"
        + "\"favorite_count\":0,\n"
        + "\"favorited\":false,\n"
        + "\"retweeeted\":false\n"
        + "}";

    Tweet tweet;
    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);

    // Mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    tweet = spyDao.findById("1");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
    assertEquals(tweet, expectedTweet);

  }

  @Test
  public void deleteById() throws IOException {
    //Exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));

    try {
      dao.deleteById("1");
      fail();
    } catch(RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "\"id\":1,\n"
        + "\"text\":\"Test\",\n"
        + "\"entities\" : {\n"
        + "    \"hashtags\":[],"
        + "    \"user_mentions\":[]"
        + "    },\n"
        + "\"coordinates\":null,\n"
        + "\"retweet_count\":0,\n"
        + "\"favorite_count\":0,\n"
        + "\"favorited\":false,\n"
        + "\"retweeeted\":false\n"
        + "}";

    Tweet tweet;

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);

    // Mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    tweet = spyDao.deleteById("1");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
    assertEquals(tweet, expectedTweet);

  }
}

