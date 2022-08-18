package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.Calendar;
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
public class TraderAccountServiceIntTest {
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  private final Trader trader1 = new Trader();
  private final Trader trader2 = new Trader();
  private final Trader trader3 = new Trader();

  TraderAccountView ta1;
  TraderAccountView ta2;
  TraderAccountView ta3;

  @Before
  public void setup() {
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

    ta1 = traderAccountService.createTraderAndAccount(trader1);
    ta2 = traderAccountService.createTraderAndAccount(trader2);
    ta3 = traderAccountService.createTraderAndAccount(trader3);

  }

  @Test
  public void createTraderAndAccount() {


    assertTrue(accountDao.existsById(ta1.getAccount().getId()));
    assertTrue(accountDao.existsById(ta2.getAccount().getId()));
    assertTrue(accountDao.existsById(ta3.getAccount().getId()));

    assertEquals(ta1.getAccount().getId(), ta1.getTrader().getId());
    assertEquals(ta2.getAccount().getId(), ta2.getTrader().getId());
    assertEquals(ta3.getAccount().getId(), ta3.getTrader().getId());

    Long test = traderDao.count();

  }

  @Test
  public void deposit() {
    Account account = traderAccountService.deposit(1, 1000.0);
    Optional<Account> optionalDepositedAccount = accountDao.findById(account.getId());
    if (!optionalDepositedAccount.isPresent()) {
      fail();
    }
    Account depositedAccount = optionalDepositedAccount.get();
    assertEquals(1000.0, depositedAccount.getAmount(), 0.001);
  }

  @Test
  public void withdraw() {
    traderAccountService.deposit(1, 1000.0);
    Account account = traderAccountService.withdraw(1, 1000.0);
    Optional<Account> optionalDepositedAccount = accountDao.findById(account.getId());
    if (!optionalDepositedAccount.isPresent()) {
      fail();
    }
    Account depositedAccount = optionalDepositedAccount.get();
    assertEquals(0.0, depositedAccount.getAmount(), 0.001);

  }

  @After
  public void deleteAll() {
    accountDao.deleteAll();
    traderDao.deleteAll();

    assertEquals(0, traderDao.count());
    assertEquals(0, accountDao.count());
  }
}