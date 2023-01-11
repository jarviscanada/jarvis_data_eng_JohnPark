package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
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
public class PositionDaoIntTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private TraderDao traderDao;

  @Autowired
  private QuoteDao quoteDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private PositionDao positionDao;

  private final SecurityOrder securityOrder1 = new SecurityOrder();
  private final SecurityOrder securityOrder2 = new SecurityOrder();
  private final SecurityOrder securityOrder3 = new SecurityOrder();
  private final Trader trader1 = new Trader();
  private final Trader trader2 = new Trader();
  private final Trader trader3 = new Trader();

  private final Quote savedQuote1 = new Quote();
  private final Quote savedQuote2 = new Quote();
  private final Quote savedQuote3 = new Quote();

  private final Account account1 = new Account();
  private final Account account2 = new Account();
  private final Account account3 = new Account();

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

    // Trader 1
    trader1.setId(1);
    trader1.setFirstName("Trader");
    trader1.setLastName("One");

    Calendar calendar1 = Calendar.getInstance();
    calendar1.set(Calendar.YEAR, 1987);
    calendar1.set(Calendar.MONTH, 8);
    calendar1.set(Calendar.DATE, 27);
    trader1.setDob(calendar1.getTime());
    trader1.setCountry("Canada");
    trader1.setEmail("trader1@abc.com");
    traderDao.save(trader1);

    // Trader 2
    trader2.setId(2);
    trader2.setFirstName("Trader");
    trader2.setLastName("Two");

    Calendar calendar2 = Calendar.getInstance();
    calendar2.set(Calendar.YEAR, 1987);
    calendar2.set(Calendar.MONTH, 7);
    calendar2.set(Calendar.DATE, 27);
    trader2.setDob(calendar2.getTime());
    trader2.setCountry("Taiwan");
    trader2.setEmail("trader2@abc.com");
    traderDao.save(trader2);

    // Trader 3
    trader3.setId(3);
    trader3.setFirstName("Trader");
    trader3.setLastName("Three");

    Calendar calendar3 = Calendar.getInstance();
    calendar3.set(Calendar.YEAR, 1987);
    calendar3.set(Calendar.MONTH, 6);
    calendar3.set(Calendar.DATE, 27);
    trader3.setDob(calendar3.getTime());
    trader3.setCountry("USA");
    trader3.setEmail("trader3@abc.com");
    traderDao.save(trader3);

    account1.setTraderId(1);
    account1.setAmount(5000);
    accountDao.save(account1);

    account2.setTraderId(2);
    account2.setAmount(6000);
    accountDao.save(account2);

    account3.setTraderId(3);
    account3.setAmount(7000);
    accountDao.save(account3);

    securityOrder1.setAccountId(1);
    securityOrder1.setStatus("FILLED");
    securityOrder1.setTicker("ONEE");
    securityOrder1.setSize(10L);
    securityOrder1.setPrice(10d);
    securityOrder1.setNotes("N/A");
    securityOrderDao.save(securityOrder1);

    securityOrder2.setAccountId(2);
    securityOrder2.setStatus("FILLED");
    securityOrder2.setTicker("TWOO");
    securityOrder2.setSize(10L);
    securityOrder2.setPrice(11d);
    securityOrder2.setNotes("N/A");
    securityOrderDao.save(securityOrder2);

    securityOrder3.setAccountId(3);
    securityOrder3.setStatus("FILLED");
    securityOrder3.setTicker("THRE");
    securityOrder3.setSize(10L);
    securityOrder3.setPrice(12d);
    securityOrder3.setNotes("N/A");
    securityOrderDao.save(securityOrder3);

  }

  @Test
  public void searchByAccountId() {
    List<Position> listOfPosition1 = positionDao.findByAccountId(1);
    if (listOfPosition1.size() != 1) {
      fail();
    }
    List<Position> listOfPosition2 = positionDao.findByAccountId(2);
    if (listOfPosition2.size() != 1) {
      fail();
    }
    List<Position> listOfPosition3 = positionDao.findByAccountId(3);
    if (listOfPosition3.size() != 1) {
      fail();
    }

    Position p1 = listOfPosition1.get(0);
    Position p2 = listOfPosition2.get(0);
    Position p3 = listOfPosition3.get(0);

    assertEquals((Integer) 1, p1.getAccountId());
    assertEquals((Integer) 2, p2.getAccountId());
    assertEquals((Integer) 3, p3.getAccountId());

    assertEquals("ONEE", p1.getTicker());
    assertEquals("TWOO", p2.getTicker());
    assertEquals("THRE", p3.getTicker());

    assertEquals((Long) 10L, p1.getPosition());
    assertEquals((Long) 10L, p2.getPosition());
    assertEquals((Long) 10L, p3.getPosition());
  }

  @Test
  public void searchByTicker() {
    List<Position> listOfPosition1 = positionDao.findByTicker("ONEE");
    if (listOfPosition1.size() != 1) {
      fail();
    }
    List<Position> listOfPosition2 = positionDao.findByTicker("TWOO");
    if (listOfPosition2.size() != 1) {
      fail();
    }
    List<Position> listOfPosition3 = positionDao.findByTicker("THRE");
    if (listOfPosition3.size() != 1) {
      fail();
    }

    Position p1 = listOfPosition1.get(0);
    Position p2 = listOfPosition2.get(0);
    Position p3 = listOfPosition3.get(0);

    assertEquals((Integer) 1, p1.getAccountId());
    assertEquals((Integer) 2, p2.getAccountId());
    assertEquals((Integer) 3, p3.getAccountId());

    assertEquals("ONEE", p1.getTicker());
    assertEquals("TWOO", p2.getTicker());
    assertEquals("THRE", p3.getTicker());

    assertEquals((Long) 10L, p1.getPosition());
    assertEquals((Long) 10L, p2.getPosition());
    assertEquals((Long) 10L, p3.getPosition());
  }

  @Test
  public void findAll() {
    List<Position> positions = positionDao.findAll();
    assertEquals(3, positions.size());

  }


  @After
  public void deleteAll() {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
    assertEquals(0, traderDao.count());
    assertEquals(0, accountDao.count());
    assertEquals(0, quoteDao.count());
    assertEquals(0, securityOrderDao.count());

  }

}