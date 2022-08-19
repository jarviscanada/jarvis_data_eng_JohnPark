package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import java.util.Calendar;
import java.util.Optional;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest extends TestCase {

  @Mock
  private TraderAccountService traderAccountService;
  @Mock
  private QuoteDao quoteDao;
  @Mock
  PositionDao positionDao;

  @InjectMocks
  private OrderService orderService;

  Trader trader;

  @Before
  public void setUp() {
    trader = new Trader();
    trader.setFirstName("Trader");
    trader.setLastName("One");

    Calendar calendar1 = Calendar.getInstance();
    calendar1.set(Calendar.YEAR, 1987);
    calendar1.set(Calendar.MONTH, 8);
    calendar1.set(Calendar.DATE, 27);
    trader.setDob(calendar1.getTime());
    trader.setCountry("Canada");
    trader.setEmail("trader1@abc.com");
  }

  @Test
  public void handleBuyMarketOrder() {
    traderAccountService.createTraderAndAccount(trader);
    traderAccountService.deposit(1, 10000.0);

    MarketOrderDto buyOrderDto = new MarketOrderDto();
    buyOrderDto.setAccountId(1);
    buyOrderDto.setSize(10L);
    buyOrderDto.setTicker("MSFT");

    orderService.executeMarketOrder(buyOrderDto);
    Optional<Position> optionalPosition = positionDao.findByIdAndTicker(buyOrderDto.getAccountId(),
        buyOrderDto.getTicker());
    if (!optionalPosition.isPresent()) {
      fail();
    }
    Position position = optionalPosition.get();

    assertEquals((Long) 10L, position.getPosition());

    MarketOrderDto sellOrderDto = new MarketOrderDto();
    buyOrderDto.setAccountId(1);
    buyOrderDto.setSize(-10L);
    buyOrderDto.setTicker("MSFT");

    orderService.executeMarketOrder(sellOrderDto);

    optionalPosition = positionDao.findByIdAndTicker(buyOrderDto.getAccountId(),
        buyOrderDto.getTicker());
    if (!optionalPosition.isPresent()) {
      fail();
    }
    position = optionalPosition.get();

    assertEquals((Long) 0L, position.getPosition());


  }
}