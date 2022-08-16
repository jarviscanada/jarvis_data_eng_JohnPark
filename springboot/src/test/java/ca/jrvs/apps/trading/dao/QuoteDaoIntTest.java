package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.text.html.Option;
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

  private final Quote savedQuote1 = new Quote();
  private final Quote savedQuote2 = new Quote();
  private final Quote savedQuote3 = new Quote();

  @Before
  public void insertOne() {
    savedQuote1.setAskPrice(10d);
    savedQuote1.setAskSize(10L);
    savedQuote1.setBidPrice(10.2);
    savedQuote1.setBidSize(10L);
    savedQuote1.setId("ONEE");
    savedQuote1.setLastPrice(10.1d);
    quoteDao.save(savedQuote1);

    savedQuote2.setAskPrice(11d);
    savedQuote2.setAskSize(11L);
    savedQuote2.setBidPrice(11.2);
    savedQuote2.setBidSize(11L);
    savedQuote2.setId("TWOO");
    savedQuote2.setLastPrice(10.1d);
    quoteDao.save(savedQuote2);

    savedQuote3.setAskPrice(12d);
    savedQuote3.setAskSize(12L);
    savedQuote3.setBidPrice(12.2);
    savedQuote3.setBidSize(12L);
    savedQuote3.setId("THRE");
    savedQuote3.setLastPrice(12.1d);
    quoteDao.save(savedQuote3);
  }

  @Test
  public void existsById() {
    assertTrue(quoteDao.existsById("ONEE"));
    assertTrue(quoteDao.existsById("TWOO"));
    assertTrue(quoteDao.existsById("THRE"));
    assertFalse(quoteDao.existsById("NENE"));

  }

  @Test
  public void searchId() {
    Optional<Quote> optionalQuote1 = quoteDao.findById("ONEE");
    if (!optionalQuote1.isPresent()) {
      fail();
    }
    Optional<Quote> optionalQuote2 = quoteDao.findById("TWOO");
    if (!optionalQuote2.isPresent()) {
      fail();
    }
    Optional<Quote> optionalQuote3 = quoteDao.findById("THRE");
    if (!optionalQuote3.isPresent()) {
      fail();
    }

    assertEquals("ONEE", optionalQuote1.get().getId());
    assertEquals("TWOO", optionalQuote2.get().getId());
    assertEquals("THRE", optionalQuote3.get().getId());

    assertFalse(quoteDao.findById("NENE").isPresent());


  }

  @Test
  public void update() {
    Quote newQuote = new Quote();
    newQuote.setAskPrice(15d);
    newQuote.setAskSize(15L);
    newQuote.setBidPrice(15.2);
    newQuote.setBidSize(15L);
    newQuote.setId("ONEE");
    newQuote.setLastPrice(15.1d);

    quoteDao.save(newQuote);
    Optional<Quote> optionalQuote = quoteDao.findById("ONEE");
    if (!optionalQuote.isPresent()) {
      fail();
    }

    Quote updatedQuote = optionalQuote.get();

    assertEquals(newQuote.getAskPrice(), updatedQuote.getAskPrice());
    assertEquals(newQuote.getAskSize(), updatedQuote.getAskSize());
    assertEquals(newQuote.getBidPrice(), updatedQuote.getBidPrice());
    assertEquals(newQuote.getBidSize(), updatedQuote.getBidSize());
    assertEquals(newQuote.getId(), updatedQuote.getId());
    assertEquals(newQuote.getLastPrice(), updatedQuote.getLastPrice());
  }

  @Test
  public void saveAll() {
    List<Quote> quotesToUpdate = new ArrayList<>();
    Quote q1 = new Quote();
    Quote q2 = new Quote();
    Quote q3 = new Quote();
    Quote q4 = new Quote();

    q1.setAskPrice(1d);
    q1.setAskSize(1L);
    q1.setBidPrice(1.2);
    q1.setBidSize(1L);
    q1.setId("AAAA");
    q1.setLastPrice(1.1d);

    q2.setAskPrice(2d);
    q2.setAskSize(2L);
    q2.setBidPrice(2.2);
    q2.setBidSize(2L);
    q2.setId("BBBB");
    q2.setLastPrice(2.1d);

    q3.setAskPrice(3d);
    q3.setAskSize(3L);
    q3.setBidPrice(3.2);
    q3.setBidSize(3L);
    q3.setId("CCCC");
    q3.setLastPrice(3.1d);

    q4.setAskPrice(20d);
    q4.setAskSize(20L);
    q4.setBidPrice(20.2);
    q4.setBidSize(20L);
    q4.setId("ONEE");
    q4.setLastPrice(20.1d);

    quotesToUpdate.add(q1);
    quotesToUpdate.add(q2);
    quotesToUpdate.add(q3);
    quotesToUpdate.add(q4);

    quoteDao.saveAll(quotesToUpdate);

    Optional<Quote> optionalQuote1 = quoteDao.findById("AAAA");
    if (!optionalQuote1.isPresent()) {
      fail();
    }
    Optional<Quote> optionalQuote2 = quoteDao.findById("BBBB");
    if (!optionalQuote2.isPresent()) {
      fail();
    }
    Optional<Quote> optionalQuote3 = quoteDao.findById("CCCC");
    if (!optionalQuote3.isPresent()) {
      fail();
    }
    Optional<Quote> optionalQuote4 = quoteDao.findById("ONEE");
    if (!optionalQuote4.isPresent()) {
      fail();
    }

    assertEquals("AAAA", optionalQuote1.get().getId());
    assertEquals("BBBB", optionalQuote2.get().getId());
    assertEquals("CCCC", optionalQuote3.get().getId());

    Quote quote4 = optionalQuote4.get();
    assertEquals((Double) 20d, quote4.getAskPrice());
    assertEquals((Long) 20L, quote4.getAskSize());
    assertEquals((Double) 20.2d, quote4.getBidPrice());
    assertEquals((Long) 20L, quote4.getBidSize());
    assertEquals("ONEE", quote4.getTicker());
    assertEquals((Double) 20.1d, quote4.getLastPrice());

    // Clean up an check
    quoteDao.save(savedQuote1);
    Long beforeSize = quoteDao.count();
    quoteDao.deleteById("AAAA");
    quoteDao.deleteById("BBBB");
    quoteDao.deleteById("CCCC");
    Long afterSize = quoteDao.count();
    assertEquals(3L, beforeSize - afterSize);

  }

  @Test
  public void findAll() {
    List<Quote> allQuotes = quoteDao.findAll();

    for (Quote quote : allQuotes) {
      if (Objects.equals(quote.getTicker(), "ONEE")) {
        assertEquals((Double) 10d, quote.getAskPrice());
        assertEquals((Long) 10L, quote.getAskSize());
        assertEquals((Double) 10.2d, quote.getBidPrice());
        assertEquals((Long) 10L, quote.getBidSize());
        assertEquals("ONEE", quote.getTicker());
        assertEquals((Double) 10.1d, quote.getLastPrice());
      } else if (Objects.equals(quote.getTicker(), "TWOO")) {
        assertEquals((Double) 11d, quote.getAskPrice());
        assertEquals((Long) 11L, quote.getAskSize());
        assertEquals((Double) 11.2d, quote.getBidPrice());
        assertEquals((Long) 11L, quote.getBidSize());
        assertEquals("TWOO", quote.getTicker());
        assertEquals((Double) 10.1d, quote.getLastPrice());
      } else if (Objects.equals(quote.getTicker(), "THRE")) {
        assertEquals((Double) 12d, quote.getAskPrice());
        assertEquals((Long) 12L, quote.getAskSize());
        assertEquals((Double) 12.2d, quote.getBidPrice());
        assertEquals((Long) 12L, quote.getBidSize());
        assertEquals("THRE", quote.getTicker());
        assertEquals((Double) 12.1d, quote.getLastPrice());
      } else {
        fail();
      }
    }
    assertEquals(3, allQuotes.size());
  }

  @Test
  public void deleteOne() {
    quoteDao.deleteById(savedQuote1.getId());
    assertEquals(2, quoteDao.count());
    quoteDao.save(savedQuote1);
  }

  @After
  public void deleteAll() {
    quoteDao.deleteAll();
    assertEquals(0, quoteDao.count());
  }
}