package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.ArrayList;
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
public class SecurityOrderDaoIntTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private TraderDao traderDao;

  @Autowired
  private QuoteDao quoteDao;

  @Autowired
  private AccountDao accountDao;

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
  public void existsById() {
    assertTrue(securityOrderDao.existsById(securityOrder1.getId()));
    assertTrue(securityOrderDao.existsById(securityOrder2.getId()));
    assertTrue(securityOrderDao.existsById(securityOrder3.getId()));

  }

  @Test
  public void searchId() {
    Optional<SecurityOrder> optionalSecurityOrder1 = securityOrderDao.findById(1);
    if (!optionalSecurityOrder1.isPresent()) {
      fail();
    }
    Optional<SecurityOrder> optionalSecurityOrder2 = securityOrderDao.findById(2);
    if (!optionalSecurityOrder2.isPresent()) {
      fail();
    }
    Optional<SecurityOrder> optionalSecurityOrder3 = securityOrderDao.findById(3);
    if (!optionalSecurityOrder3.isPresent()) {
      fail();
    }

    assertEquals((Integer) 1, optionalSecurityOrder1.get().getId());
    assertEquals((Integer) 2, optionalSecurityOrder2.get().getId());
    assertEquals((Integer) 3, optionalSecurityOrder3.get().getId());

    assertFalse(securityOrderDao.findById(4).isPresent());
  }

  @Test
  public void deleteById() {
    Trader toDeleteTrader = new Trader();
    toDeleteTrader.setFirstName("Delete");
    toDeleteTrader.setLastName("Trader");

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1987);
    calendar.set(Calendar.MONTH, 7);
    calendar.set(Calendar.DATE, 27);
    toDeleteTrader.setDob(calendar.getTime());
    toDeleteTrader.setCountry("Canada");
    toDeleteTrader.setEmail("toDelete@abc.com");
    traderDao.save(toDeleteTrader);

    assertTrue(traderDao.existsById(4));


    Account toDeleteAccount = new Account();
    toDeleteAccount.setTraderId(4);
    toDeleteAccount.setAmount(30);
    accountDao.save(toDeleteAccount);

    assertTrue(accountDao.existsById(4));

    Quote toDeleteQuote = new Quote();
    toDeleteQuote.setAskPrice(14d);
    toDeleteQuote.setAskSize(14L);
    toDeleteQuote.setBidPrice(14.2);
    toDeleteQuote.setBidSize(14L);
    toDeleteQuote.setId("DELE");
    toDeleteQuote.setLastPrice(14.1d);
    quoteDao.save(toDeleteQuote);

    assertTrue(quoteDao.existsById("DELE"));

    SecurityOrder toDeleteSecurityOrder = new SecurityOrder();
    toDeleteSecurityOrder.setAccountId(4);
    toDeleteSecurityOrder.setStatus("FILLED");
    toDeleteSecurityOrder.setTicker("DELE");
    toDeleteSecurityOrder.setSize(10L);
    toDeleteSecurityOrder.setPrice(10d);
    toDeleteSecurityOrder.setNotes("N/A");
    securityOrderDao.save(toDeleteSecurityOrder);

    securityOrderDao.deleteById(4);
    assertFalse(securityOrderDao.existsById(4));

    quoteDao.deleteById("DELE");
    assertFalse(quoteDao.existsById("DELE"));

    accountDao.deleteById(4);
    assertFalse(accountDao.existsById(4));

    traderDao.deleteById(4);
    assertFalse(traderDao.existsById(4));


  }

  @Test
  public void findAll() {
    List<SecurityOrder> securityOrders = securityOrderDao.findAll();

    for (SecurityOrder securityOrder : securityOrders) {
      if (securityOrder.getId() == 1) {
        assertEquals((Integer) 1, securityOrder.getAccountId());
        assertEquals("FILLED", securityOrder.getStatus());
        assertEquals("ONEE", securityOrder.getTicker());
        assertEquals((Long) 10L, securityOrder.getSize());
        assertEquals(10d, securityOrder.getPrice(), 0.01);

      } else if (securityOrder.getId() == 2) {
        assertEquals((Integer) 2, securityOrder.getAccountId());
        assertEquals("FILLED", securityOrder.getStatus());
        assertEquals("TWOO", securityOrder.getTicker());
        assertEquals((Long) 10L, securityOrder.getSize());
        assertEquals(11d, securityOrder.getPrice(), 0.01);

      } else if (securityOrder.getId() == 3) {
        assertEquals((Integer) 3, securityOrder.getAccountId());
        assertEquals("FILLED", securityOrder.getStatus());
        assertEquals("THRE", securityOrder.getTicker());
        assertEquals((Long) 10L, securityOrder.getSize());
        assertEquals(12d, securityOrder.getPrice(), 0.01);
      } else {
        fail();
      }
    }
    assertEquals(3, securityOrders.size());
  }


  @Test
  public void saveAll() {
    List<SecurityOrder> sosToUpdate = new ArrayList<>();
    SecurityOrder s1 = new SecurityOrder();
    SecurityOrder s2 = new SecurityOrder();
    SecurityOrder s3 = new SecurityOrder();

    s1.setId(1);
    s1.setAccountId(1);
    s1.setStatus("FILLED");
    s1.setTicker("ONEE");
    s1.setSize(11L);
    s1.setPrice(10d);
    s1.setNotes("N/A");

    s2.setId(2);
    s2.setAccountId(2);
    s2.setStatus("FILLED");
    s2.setTicker("TWOO");
    s2.setSize(11L);
    s2.setPrice(11d);
    s2.setNotes("N/A");

    s3.setId(3);
    s3.setAccountId(3);
    s3.setStatus("FILLED");
    s3.setTicker("THRE");
    s3.setSize(11L);
    s3.setPrice(12d);
    s3.setNotes("N/A");

    Trader toDeleteTrader = new Trader();
    toDeleteTrader.setFirstName("Delete");
    toDeleteTrader.setLastName("Trader");

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1987);
    calendar.set(Calendar.MONTH, 7);
    calendar.set(Calendar.DATE, 27);
    toDeleteTrader.setDob(calendar.getTime());
    toDeleteTrader.setCountry("Canada");
    toDeleteTrader.setEmail("toDelete@abc.com");
    traderDao.save(toDeleteTrader);

    assertTrue(traderDao.existsById(4));


    Account toDeleteAccount = new Account();
    toDeleteAccount.setTraderId(4);
    toDeleteAccount.setAmount(30);
    accountDao.save(toDeleteAccount);

    assertTrue(accountDao.existsById(4));

    Quote toDeleteQuote = new Quote();
    toDeleteQuote.setAskPrice(14d);
    toDeleteQuote.setAskSize(14L);
    toDeleteQuote.setBidPrice(14.2);
    toDeleteQuote.setBidSize(14L);
    toDeleteQuote.setId("DELE");
    toDeleteQuote.setLastPrice(14.1d);
    quoteDao.save(toDeleteQuote);

    assertTrue(quoteDao.existsById("DELE"));

    SecurityOrder toDeleteSecurityOrder = new SecurityOrder();
    toDeleteSecurityOrder.setAccountId(4);
    toDeleteSecurityOrder.setStatus("FILLED");
    toDeleteSecurityOrder.setTicker("DELE");
    toDeleteSecurityOrder.setSize(10L);
    toDeleteSecurityOrder.setPrice(10d);
    toDeleteSecurityOrder.setNotes("N/A");
    securityOrderDao.save(toDeleteSecurityOrder);

    sosToUpdate.add(s1);
    sosToUpdate.add(s2);
    sosToUpdate.add(s3);
    sosToUpdate.add(toDeleteSecurityOrder);

    securityOrderDao.saveAll(sosToUpdate);

    Optional<SecurityOrder> optionalSecurityOrder1 = securityOrderDao.findById(1);
    if (!optionalSecurityOrder1.isPresent()) {
      fail();
    }
    Optional<SecurityOrder> optionalSecurityOrder2 = securityOrderDao.findById(2);
    if (!optionalSecurityOrder2.isPresent()) {
      fail();
    }
    Optional<SecurityOrder> optionalSecurityOrder3 = securityOrderDao.findById(3);
    if (!optionalSecurityOrder3.isPresent()) {
      fail();
    }
    Optional<SecurityOrder> optionalSecurityOrder4 = securityOrderDao.findById(4);
    if (!optionalSecurityOrder4.isPresent()) {
      fail();
    }

    assertEquals((Long) 11L, optionalSecurityOrder1.get().getSize());
    assertEquals((Long) 11L, optionalSecurityOrder2.get().getSize());
    assertEquals((Long) 11L, optionalSecurityOrder3.get().getSize());
    assertEquals((Long) 10L, optionalSecurityOrder4.get().getSize());

    Long beforeSize = securityOrderDao.count();
    securityOrderDao.deleteById(4);
    Long afterSize = securityOrderDao.count();

    assertEquals(1L, beforeSize - afterSize);

    securityOrderDao.save(securityOrder1);
    securityOrderDao.save(securityOrder2);
    securityOrderDao.save(securityOrder3);


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