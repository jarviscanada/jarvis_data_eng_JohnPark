package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.text.html.Option;
import junit.framework.TestCase;
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
public class QuoteServiceTest extends TestCase {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();

    Quote msftQuote = new Quote();
    msftQuote.setId("MSFT");
    msftQuote.setLastPrice(-1.0);
    msftQuote.setBidPrice(-1.0);
    msftQuote.setBidSize(-1L);
    msftQuote.setAskPrice(-1.0);
    msftQuote.setAskSize(-1L);

    quoteDao.save(msftQuote);

    Quote aaplQuote = new Quote();
    aaplQuote.setId("AAPL");
    aaplQuote.setLastPrice(-1.0);
    aaplQuote.setBidPrice(-1.0);
    aaplQuote.setBidSize(-1L);
    aaplQuote.setAskPrice(-1.0);
    aaplQuote.setAskSize(-1L);

    quoteDao.save(aaplQuote);

    Quote amznQuote = new Quote();
    amznQuote.setId("AMZN");
    amznQuote.setLastPrice(-1.0);
    amznQuote.setBidPrice(-1.0);
    amznQuote.setBidSize(-1L);
    amznQuote.setAskPrice(-1.0);
    amznQuote.setAskSize(-1L);

    quoteDao.save(amznQuote);

    Quote metaQuote = new Quote();
    metaQuote.setId("META");
    metaQuote.setLastPrice(-1.0);
    metaQuote.setBidPrice(-1.0);
    metaQuote.setBidSize(-1L);
    metaQuote.setAskPrice(-1.0);
    metaQuote.setAskSize(-1L);

    quoteDao.save(metaQuote);

  }


  @Test
  public void findIexQuoteByTicker() {
    IexQuote metaIexQuote = quoteService.findIexQuoteByTicker("META");
    IexQuote amznIexQuote = quoteService.findIexQuoteByTicker("AMZN");

    assertEquals("META", metaIexQuote.getSymbol());
    assertEquals("AMZN", amznIexQuote.getSymbol());
  }

  @Test
  public void updateMarketData() {
    quoteService.updateMarketData();

    List<String> tickers = new ArrayList<>(Arrays.asList("META", "AMZN", "AAPL", "MSFT"));

    tickers.forEach(ticker -> {
      Optional<Quote> optionalQuote = quoteDao.findById(ticker);
      if (!optionalQuote.isPresent()) {
        fail();
      }
      Quote quote = optionalQuote.get();
      assertNotSame(-1.0, quote.getLastPrice());
      assertNotSame(-1.0, quote.getBidPrice());
      assertNotSame(-1L, quote.getBidSize());
      assertNotSame(-1.0, quote.getAskPrice());
      assertNotSame(-1L, quote.getAskSize());

    });

  }

  @Test
  public void buildQuoteFromIexQuote() {
    IexQuote fbIexQuote = quoteService.findIexQuoteByTicker("META");
    IexQuote amznIexQuote = quoteService.findIexQuoteByTicker("AMZN");

    Quote metaQuote = QuoteService.buildQuoteFromIexQuote(fbIexQuote);
    Quote amznQuote = QuoteService.buildQuoteFromIexQuote(amznIexQuote);

    assertEquals(metaQuote.getTicker(), fbIexQuote.getSymbol());
    assertEquals(amznQuote.getTicker(), amznIexQuote.getSymbol());

  }

  @Test
  public void saveQuote() {
    Quote metaQuote = quoteService.saveQuote("META");
    Quote amznQuote = quoteService.saveQuote("AMZN");

    assertEquals("META", metaQuote.getTicker());
    assertEquals("AMZN", amznQuote.getTicker());

  }

  @Test
  public void saveQuotes() {
    List<String> tickers = new ArrayList<>(Arrays.asList("META", "AMZN", "AAPL"));
    List<Quote> quotes = quoteService.saveQuotes(tickers);

    quotes.forEach(quote -> {
      if (!Objects.equals(quote.getTicker(), "META") && !Objects.equals(quote.getTicker(), "AMZN")
          && !Objects.equals(quote.getTicker(), "AAPL")) {
        fail();
      }
    });
  }
}