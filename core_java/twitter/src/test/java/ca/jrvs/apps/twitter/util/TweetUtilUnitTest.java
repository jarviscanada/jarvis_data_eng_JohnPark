package ca.jrvs.apps.twitter.util;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;

public class TweetUtilUnitTest {

  @Test
  public void buildTweet() {
    Tweet tweet = TweetUtil.buildTweet("Test", 32.0, -20.0);
    assertNotNull(tweet);
    assertEquals("Test", tweet.getText());
    assertEquals((Double) 32.0, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals( (Double) (-20.0), tweet.getCoordinates().getCoordinates().get(1));
  }

  @Test
  public void validatePostTweet() {
    try {
      String text = new String(new char[141]).replace("\0", "A");
      Tweet tweet = TweetUtil.buildTweet(text, 1.0, 1.0);
      TweetUtil.validatePostTweet(tweet);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      Tweet tweet = TweetUtil.buildTweet("Test", -181.0, 1.0);
      TweetUtil.validatePostTweet(tweet);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      Tweet tweet = TweetUtil.buildTweet("Test", 181.0, 1.0);
      TweetUtil.validatePostTweet(tweet);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      Tweet tweet = TweetUtil.buildTweet("Test", 1.0, -91.0);
      TweetUtil.validatePostTweet(tweet);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      Tweet tweet = TweetUtil.buildTweet("Test", 1.0, 91.0);
      TweetUtil.validatePostTweet(tweet);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    Tweet tweet = TweetUtil.buildTweet("Test", 32.0, -20.0);
    TweetUtil.validatePostTweet(tweet);
  }

  @Test
  public void validateTweetId() {
    TweetUtil.validateTweetId("1");
    TweetUtil.validateTweetId("2");

    try {
      TweetUtil.validateTweetId("a");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}