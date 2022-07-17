package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");
  HttpHelper httpHelper;

  @Before
  public void init() {
    httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
  }

  @Test
  public void httpPost() throws URISyntaxException, OAuthException, IOException {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    String status = "Test Test in Test";
    HttpResponse response = httpHelper.httpPost(
        new URI("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(
            status)));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws URISyntaxException, OAuthException, IOException {
    HttpResponse response = httpHelper.httpGet(new URI(
        "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=JohnPar93379539"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}