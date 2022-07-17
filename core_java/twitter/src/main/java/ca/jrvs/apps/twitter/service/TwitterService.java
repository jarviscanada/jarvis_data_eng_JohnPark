package ca.jrvs.apps.twitter.service;

import static ca.jrvs.apps.twitter.util.TweetUtil.validatePostTweet;
import static ca.jrvs.apps.twitter.util.TweetUtil.validateTweetId;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) throws IllegalArgumentException{
    validatePostTweet(tweet);

    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateTweetId(id);

    Tweet response = (Tweet) dao.findById(id);

    if (fields == null || fields.length == 0) {
      return response;
    }

    Tweet tweet = new Tweet();
    Arrays.stream(fields).forEach(field -> {
      switch(field) {
        case "id":
          tweet.setId(response.getId());
          break;
        case "id_str":
          tweet.setIdStr(response.getIdStr());
          break;
        case "text":
          tweet.setText(response.getText());
          break;
        case "entities":
          tweet.setEntities(response.getEntities());
          break;
        case "coordinates":
          tweet.setCoordinates(response.getCoordinates());
          break;
        case "retweet_count":
          tweet.setRetweetCount(response.getRetweetCount());
          break;
        case "favorite_count":
          tweet.setFavoriteCount(response.getFavoriteCount());
          break;
        case "favorited":
          tweet.setFavorited(response.getFavorited());
          break;
        case "retweeted":
          tweet.setRetweeted(response.getRetweeted());
          break;
        default:
          throw new IllegalArgumentException("Invalid optional field:" + field);
      }
    });

    return tweet;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    Arrays.stream(ids).forEach(TweetUtil::validateTweetId);

    return Arrays.stream(ids).map(id -> (Tweet) dao.deleteById(id)).collect(Collectors.toList());
  }
}
