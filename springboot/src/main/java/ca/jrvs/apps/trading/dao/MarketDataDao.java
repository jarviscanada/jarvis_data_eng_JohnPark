package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.dao.DataRetrievalFailureException;

public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATCH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATCH + marketDataConfig.getToken();
  }

  /**
   * Get an IexQuote (helper method which class findAllById)
   * @param ticker
   * @throws IllegalArgumentException if a given ticker is invalid
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  /**
   * Get quotes from IEX
   * @param tickers is a list of tickers
   * @return a list of IexQuote object
   * @throws IllegalArgumentException if any ticker is invalid or tickers is empty
   * @throws  DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    if (Iterables.size(tickers) == 0) {
      throw new IllegalArgumentException("Empty tickers");
    }

    String tickersInString = String.join(",", tickers);
    String uri = String.format(IEX_BATCH_URL, tickersInString);

    String response = executeHttpGet(uri).orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

    //Array of JSON documents
    JSONObject IexQuotesJson = new JSONObject(response);

    //Get number of documents
    if (IexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid ticker");
    }

    ObjectMapper mapper = new ObjectMapper();
    Stream<String> tickersKey = StreamSupport.stream(tickers.spliterator(), false);

    return tickersKey.map(ticker -> {
      try {
        if (!IexQuotesJson.has(ticker)) {
          throw new IllegalArgumentException("Invalid ticker");
        }
        String quoteStr = IexQuotesJson.getJSONObject(ticker).get("quote").toString();
        return mapper.readValue(quoteStr, IexQuote.class);
      } catch (JSONException | IOException e) {
        throw new DataRetrievalFailureException("Failed to retrieve data", e);
      }
    }).collect(Collectors.toList());
  }

  /**
   * Execute a get and return http entity/body as a string
   *
   * Tip: use EntityUtils.toString to process HTTP entity
   *
   * @param url resource URL
   * @return http response body or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) {
    CloseableHttpClient httpClient = getHttpClient();
    HttpUriRequest request = new HttpGet(url);
    try {
       HttpResponse httpResponse = httpClient.execute(request);
       String jsonStr = EntityUtils.toString(httpResponse.getEntity());
       return Optional.of(jsonStr);
    } catch (IOException e) {
      throw new DataRetrievalFailureException("HTTP request have failed", e);
    }
  }

  /**
   * Borrow a HTTP client from the httpClientConnectionManager
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        //prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true)
        .build();
  }

  @Override
  public <S extends IexQuote> S save(S entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(IexQuote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
