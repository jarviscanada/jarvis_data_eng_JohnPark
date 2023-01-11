package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection. Discuss source code if time permit
     */
    httpClient = new DefaultHttpClient();
  }

  /**
   * Default constructor (not used for now)
   */
  public TwitterHttpHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    httpClient = new DefaultHttpClient();
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    return executeHttpRequest(HttpMethod.POST, uri);
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    return executeHttpRequest(HttpMethod.GET, uri);
  }

  private HttpResponse executeHttpRequest(HttpMethod method, URI uri) {
    HttpUriRequest request;

    if (method == HttpMethod.GET) {
      request = new HttpGet(uri);
    } else if (method == HttpMethod.POST) {
      request = new HttpPost(uri);
    } else {
      throw new IllegalArgumentException("Not a valid Http Method: " + method.name());
    }

    try {
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (OAuthException e) {
      e.printStackTrace();
      throw new RuntimeException("Authorization with O-Auth failed ", e);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Issue with sending request", e);
    }
  }

//  public static void main(String[] args) throws URISyntaxException, OAuthException, IOException {
//    String consumerKey = System.getenv("consumerKey");
//    String consumerSecret = System.getenv("consumerSecret");
//    String accessToken = System.getenv("accessToken");
//    String tokenSecret = System.getenv("tokenSecret");
//
//    System.out.println("consumerKey: " + consumerKey);
//    System.out.println("consumerSecret: " + consumerSecret);
//    System.out.println("accessToken: " + accessToken);
//    System.out.println("tokenSecret: " + tokenSecret);
//
//    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
//        tokenSecret);
//
//    HttpResponse response;
//
//    //Test Get Method
//    System.out.println("======== Test Get Method ========");
//    response = httpHelper.httpGet(new URI(
//        "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=JohnPar93379539"));
//    System.out.println(EntityUtils.toString(response.getEntity()));
//
//    //Test Post Method
//    System.out.println("======== Test Post Method ========");
//    PercentEscaper percentEscaper = new PercentEscaper("", false);
//    String status = "Test Test Test";
//    response = httpHelper.httpPost(
//        new URI("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(
//            status)));
//    System.out.println(EntityUtils.toString(response.getEntity()));
//  }
}
