package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
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
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;



  private Quote savedQuote = new Quote();

  @Before
  public void insertOne() {
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10L);
    savedQuote.setBidPrice(10.2);
    savedQuote.setBidSize(10L);
    savedQuote.setId("AAPL");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @Test
  public void test() {
    assertEquals("AAPL", savedQuote.getId());
  }

  @After
  public void deleteOne() {
    quoteDao.
        deleteById(savedQuote.getId());
  }
}