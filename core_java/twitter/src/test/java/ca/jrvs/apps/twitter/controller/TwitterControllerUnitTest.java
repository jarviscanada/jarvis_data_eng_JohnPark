package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  @Captor
  ArgumentCaptor<Tweet> tweetCaptor;

  @Captor
  ArgumentCaptor<String> idCaptor;

  @Captor
  ArgumentCaptor<String[]> idsCaptor;

  @Test
  public void postTweet() {
    String[] args = new String[]{"post", "Controller Unit testing", "32.0:48.0"};
    controller.postTweet(args);
    verify(service, times(1)).postTweet(tweetCaptor.capture());
    Tweet tweet = tweetCaptor.getValue();
    assertEquals("Controller Unit testing", tweet.getText());
    assertEquals((Double) 48.0, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals((Double) 32.0, tweet.getCoordinates().getCoordinates().get(1));

    //Testing invalid arguments:

    // Wrong number of arguments
    args = new String[]{"post"};
    try {
      controller.postTweet(args);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    }

    // Wrong initiate
    args = new String[]{"poost", "Hello World!", "21.0:23.5"};
    try {
      controller.postTweet(args);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void showTweet() {
    String[] args = new String[]{"show", "1"};
    controller.showTweet(args);
    verify(service, times(1)).showTweet(idCaptor.capture(), eq(null));
    String id = idCaptor.getValue();
    assertEquals("1", id);


    // Invalid argument tests

    //Wrong number of arguments
    args = new String[]{"show"};
    try {
      controller.showTweet(args);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    }

    // Not a valid input
    args = new String[]{"sshow", "1"};
    try {
      controller.showTweet(args);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTweet() {
    String[] args = new String[]{"delete", "1", "2"};
    controller.deleteTweet(args);
    verify(service, times(1)).deleteTweets(idsCaptor.capture());
    String[] ids = idsCaptor.getValue();
    assertNotNull(ids);
    assertEquals(ids.length, 2);
    assertEquals(ids[0], "1");
    assertEquals(ids[1], "2");


    //Test invalid arguments:

    // Wrong number of arguments
    args = new String[]{"delete"};
    try {
      controller.deleteTweet(args);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    }

    // Not valid argument
    args = new String[]{"ddeellete", "1"};
    try {
      controller.deleteTweet(args);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    }

  }
}