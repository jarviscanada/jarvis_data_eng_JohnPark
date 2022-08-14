package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceTest extends TestCase {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @Test
  public void updateMarketData() {
  }

  @Test
  public void buildQuoteFromIexQuote() {
  }

  @Test
  public void saveQuotes() {
  }

  @Test
  public void saveQuote() {
  }

  @Test
  public void findIexQuoteByTicker() {
  }

  @Test
  public void findAllQuotes() {
  }
}