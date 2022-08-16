package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
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
public class TraderDaoIntTest {

  @Autowired
  private TraderDao traderDao;

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

  }


  @Test
  public void existsById() {
    assertTrue(traderDao.existsById(1));
    assertTrue(traderDao.existsById(2));
    assertTrue(traderDao.existsById(3));
    assertFalse(traderDao.existsById(4));
  }

  @Test
  public void searchId() {
    Optional<Trader> optionalTrader1 = traderDao.findById(1);
    if (!optionalTrader1.isPresent()) {
      fail();
    }
    Optional<Trader> optionalTrader2 = traderDao.findById(2);
    if (!optionalTrader2.isPresent()) {
      fail();
    }
    Optional<Trader> optionalTrader3 = traderDao.findById(3);
    if (!optionalTrader3.isPresent()) {
      fail();
    }

    assertEquals((Integer) 1, optionalTrader1.get().getId());
    assertEquals((Integer) 2, optionalTrader2.get().getId());
    assertEquals((Integer) 3, optionalTrader3.get().getId());

    assertFalse(traderDao.findById(4).isPresent());

  }


  @Test
  public void findAll() {
    List<Trader> allTraders = traderDao.findAll();

    for (Trader trader : allTraders) {
      if (trader.getId() == 1) {
        assertEquals("Trader", trader.getFirstName());
        assertEquals("One", trader.getLastName());
        assertEquals("Canada", trader.getCountry());
        assertEquals("trader1@abc.com", trader.getEmail());
      } else if (trader.getId() == 2) {
        assertEquals("Trader", trader.getFirstName());
        assertEquals("Two", trader.getLastName());
        assertEquals("Taiwan", trader.getCountry());
        assertEquals("trader2@abc.com", trader.getEmail());
      } else if (trader.getId() == 3) {
        assertEquals("Trader", trader.getFirstName());
        assertEquals("Three", trader.getLastName());
        assertEquals("USA", trader.getCountry());
        assertEquals("trader3@abc.com", trader.getEmail());
      } else {
        fail();
      }
    }
    assertEquals(3, allTraders.size());
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
    System.out.println(toDeleteTrader.getId());
    traderDao.save(toDeleteTrader);

    assertTrue(traderDao.existsById(4));
    traderDao.deleteById(4);
    assertFalse(traderDao.existsById(4));

  }

  @After
  public void deleteAll() {
    traderDao.deleteAll();
    assertEquals(0, traderDao.count());

  }

}