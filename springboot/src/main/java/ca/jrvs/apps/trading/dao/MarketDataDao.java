package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Optional;
import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

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

  @Override
  public <S extends IexQuote> S save(S entity) {
    return null;
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<IexQuote> findById(String s) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public Iterable<IexQuote> findAll() {
    return null;
  }

  @Override
  public Iterable<IexQuote> findAllById(Iterable<String> strings) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(String s) {

  }

  @Override
  public void delete(IexQuote entity) {

  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> entities) {

  }

  @Override
  public void deleteAll() {

  }
}
