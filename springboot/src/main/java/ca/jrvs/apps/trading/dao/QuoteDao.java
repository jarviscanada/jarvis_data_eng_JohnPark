package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  public static void main(String[] args) {
    System.out.println("Creating apacheDataSource");
    String url = System.getenv("PSQL_URL");
    System.out.println(url);
    String user = System.getenv("PSQL_USER");
    System.out.println(user);
    String password = System.getenv("PSQL_PASSWORD");
    System.out.println(password);
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);
    QuoteDao quoteDao = new QuoteDao(basicDataSource);

    Quote testQuote = new Quote();
    testQuote.setId("TEST");
    testQuote.setTicker("TEST");
    testQuote.setLastPrice(500.0);
    testQuote.setBidPrice(0.0);
    testQuote.setAskPrice(0.0);
    testQuote.setBidSize(0L);
    testQuote.setAskSize(0L);

    quoteDao.addOne(testQuote);

    quoteDao.deleteById("TEST");

    Quote res = quoteDao.findById("AMZN").get();
    System.out.println(res.getId());
    System.out.println(res.getTicker());
    System.out.println(res.getLastPrice());

    quoteDao.deleteAll();

  }

  /**
   * hint: http://bit.ly/2sDz8hq DataAccessException family
   *
   * @throws DataAccessException for unexpected SQL result or SQL execution failure
   */
  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getTicker())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves one quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * helper method that updates one quote
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE " + TABLE_NAME + " SET last_price=?, bid_price=?,"
        + "bid_size=?, ask_price=?, ask_size=? WHERE " + ID_COLUMN_NAME + " = ?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * helper method that makes sql update values objects
   *
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
        quote.getAskPrice(), quote.getAskSize(), quote.getTicker()};
  }

  @Override
  public <S extends Quote> List<S> saveAll(Iterable<S> quotes) {
    String updateSql = "UPDATE " + TABLE_NAME
        + " SET last_price=?, bid_price=?, bid_size=?, ask_price=?, ask_size=? WHERE "
        + ID_COLUMN_NAME + " =?";
    List<Object[]> batch = new ArrayList<>();
    quotes.forEach(quote -> {
      if (!existsById(quote.getTicker())) {
        throw new IllegalArgumentException("Ticker not found:" + quote.getTicker());
      }
      batch.add(new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
          quote.getAskPrice(), quote.getAskSize(), quote.getTicker()});
    });
    int[] rows = jdbcTemplate.batchUpdate(updateSql, batch);
    int totalRow = Arrays.stream(rows).sum();
    if (totalRow != Iterables.size(quotes)) {
      throw new IncorrectResultSizeDataAccessException("Number of rows ", Iterables.size(quotes),
          totalRow);
    }

    return StreamSupport.stream(quotes.spliterator(), false)
        .collect(Collectors.toList());
  }

  /**
   * Find a quote by ticker
   *
   * @param ticker name
   * @return quote or Optional.empty if not found
   */
  @Override
  public Optional<Quote> findById(String ticker) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ?";
    Quote resQuote;
    try {
      resQuote = jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Quote.class), ticker);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
    return Optional.ofNullable(resQuote);
  }

  /**
   * return all quotes
   *
   * @throws DataAccessException if failed to update
   */
  @Override
  public List<Quote> findAll() {
    String selectAllSql = "SELECT * FROM " + TABLE_NAME;

    return jdbcTemplate.queryForList(selectAllSql, Quote.class);
  }

  @Override
  public boolean existsById(String ticker) {
    return findById(ticker).isPresent();
  }

  @Override
  public long count() {
    String countSql = "COUNT(*) FROM " + TABLE_NAME;

    Long res = jdbcTemplate.queryForObject(countSql, Long.class);
    if (res == null) {
      return 0;
    }
    return res;
  }

  @Override
  public void deleteById(String ticker) {
    if (!(existsById(ticker))) {
      throw new IllegalArgumentException("ID not available");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
    jdbcTemplate.update(deleteSql, ticker);
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }


}
