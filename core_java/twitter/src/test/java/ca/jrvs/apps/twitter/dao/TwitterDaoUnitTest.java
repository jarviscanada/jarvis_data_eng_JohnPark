package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;


import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TwitterDaoUnitTest {
  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void create() {
  }

  @Test
  public void findById() {
  }

  @Test
  public void deleteById() {
  }



  @Test
  public void showTweet() throws Exception {
    //test failed request
    String hashTag = "#abc";
    String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
    Double lon = -1d;
    Double lat = 1d;
    //exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(TweetUtil.buildTweet(text, lon, lat));
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //Test happy path
    //however, we don't want to call parseResponseBody.
    //we will make a spyDao which can fake parseResponseBody return value
//    String tweetJsonStr = "{\n"
//        + "    \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
//        + "    \"id\":1097607853932564480,\n"
//        + "    \"id_str\":\"1097607853932564480\",\n"
//        + "    \"
//        + "}";
  }



}

