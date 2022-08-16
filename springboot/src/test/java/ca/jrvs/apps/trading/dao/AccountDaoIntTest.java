package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  private final Account account1 = new Account();
  private final Account account2 = new Account();
  private final Account account3 = new Account();

  private final Trader trader1 = new Trader();
  private final Trader trader2 = new Trader();
  private final Trader trader3 = new Trader();


  @Before
  public void insertOne() {
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

  }

  @Test
  public void existsById() {
    assertTrue(accountDao.existsById(1));
    assertTrue(accountDao.existsById(2));
    assertTrue(accountDao.existsById(3));

  }

  @Test
  public void searchId() {
    Optional<Account> optionalAccount1 = accountDao.findById(1);
    if (!optionalAccount1.isPresent()) {
      fail();
    }
    Optional<Account> optionalAccount2 = accountDao.findById(2);
    if (!optionalAccount2.isPresent()) {
      fail();
    }
    Optional<Account> optionalAccount3 = accountDao.findById(3);
    if (!optionalAccount3.isPresent()) {
      fail();
    }

    assertEquals((Integer) 1, optionalAccount1.get().getId());
    assertEquals((Integer) 2, optionalAccount2.get().getId());
    assertEquals((Integer) 3, optionalAccount3.get().getId());

    assertFalse(traderDao.findById(4).isPresent());
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

    accountDao.deleteById(4);
    assertFalse(accountDao.existsById(4));


    traderDao.deleteById(4);
    assertFalse(traderDao.existsById(4));
  }

  @Test
  public void findAll() {
    List<Account> allAccounts = accountDao.findAll();

    for (Account account : allAccounts) {
      if (account.getId() == 1) {
        assertEquals((Integer) 1, account.getTraderId());
        assertEquals(5000d, account.getAmount(), 0.01);
      } else if (account.getId() == 2) {
        assertEquals((Integer) 2, account.getTraderId());
        assertEquals(6000d, account.getAmount(), 0.01);

      } else if (account.getId() == 3) {
        assertEquals((Integer) 3, account.getTraderId());
        assertEquals(7000d, account.getAmount(), 0.01);
      } else {
        fail();
      }
    }
    assertEquals(3, allAccounts.size());
  }


  @After
  public void deleteAll() {
    accountDao.deleteAll();
    traderDao.deleteAll();
    assertEquals(0, traderDao.count());
    assertEquals(0, accountDao.count());

  }


}