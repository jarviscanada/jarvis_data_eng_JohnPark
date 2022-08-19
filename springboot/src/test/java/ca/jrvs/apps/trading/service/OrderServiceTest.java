package ca.jrvs.apps.trading.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import java.util.Calendar;
import java.util.Optional;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest extends TestCase {
  //capture parameter when calling securityOrderDao.save
  @Captor
  ArgumentCaptor<SecurityOrder> captorSecurityOrder;


  @Mock
  private AccountDao accountDao;
  @Mock
  private SecurityOrderDao securityOrderDao;
  @Mock
  private QuoteDao quoteDao;
  @Mock
  private PositionDao positionDao;

  //injecting mocked dependencies to the testing class via constructor
  @InjectMocks
  private OrderService orderService;



  @Test
  public void executeMarketOrderBuy() {
//    traderAccountService.createTraderAndAccount(trader);
//    traderAccountService.deposit(1, 10000.0);

    MarketOrderDto orderDtoNilSize = new MarketOrderDto();
    orderDtoNilSize.setAccountId(1);
    orderDtoNilSize.setSize(0L);
    orderDtoNilSize.setTicker("MSFT");

    try {
      orderService.executeMarketOrder(orderDtoNilSize);
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    } catch(Exception e) {
      fail();
    }

    Account account = new Account();
    account.setAmount(10000.0);
    account.setId(1);
    account.setTraderId(1);

    Quote quote = new Quote();
    quote.setId("MSFT");
    quote.setAskPrice(0.0);
    quote.setAskSize(0L);
    quote.setBidSize(0L);
    quote.setBidPrice(0.0);
    quote.setLastPrice(290.17);

    SecurityOrder securityOrderBuy = new SecurityOrder();
    securityOrderBuy.setAccountId(1);
    securityOrderBuy.setStatus(Status.FILLED);
    securityOrderBuy.setPrice(290.17);
    securityOrderBuy.setTicker("MSFT");
    securityOrderBuy.setSize(10L);

    MarketOrderDto buyOrderDto = new MarketOrderDto();
    buyOrderDto.setAccountId(1);
    buyOrderDto.setSize(10L);
    buyOrderDto.setTicker("MSFT");



    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    when(quoteDao.findById(any())).thenReturn(Optional.of(quote));
    when(securityOrderDao.save(any())).thenReturn(securityOrderBuy);


    SecurityOrder securityOrderResult = orderService.executeMarketOrder(buyOrderDto);
    verify(securityOrderDao).save(captorSecurityOrder.capture());


    assertEquals(securityOrderBuy.getAccountId(), securityOrderResult.getAccountId());
    assertEquals(securityOrderBuy.getTicker(), securityOrderResult.getTicker());
    assertEquals(securityOrderBuy.getStatus(), securityOrderResult.getStatus());
    assertEquals(securityOrderBuy.getPrice(), securityOrderResult.getPrice());
    assertEquals(securityOrderBuy.getSize(), securityOrderResult.getSize());

    assertEquals(captorSecurityOrder.getValue().getAccountId(), securityOrderBuy.getAccountId());
    assertEquals(captorSecurityOrder.getValue().getTicker(), securityOrderBuy.getTicker());
    assertEquals(captorSecurityOrder.getValue().getStatus(), securityOrderBuy.getStatus());
    assertEquals(captorSecurityOrder.getValue().getPrice(), securityOrderBuy.getPrice());
    assertEquals(captorSecurityOrder.getValue().getSize(), securityOrderBuy.getSize());





  }

  @Test
  public void executeMarketOrderSell() {

    Account account = new Account();
    account.setAmount(10000.0);
    account.setId(1);
    account.setTraderId(1);

    Quote quote = new Quote();
    quote.setId("MSFT");
    quote.setAskPrice(0.0);
    quote.setAskSize(0L);
    quote.setBidSize(0L);
    quote.setBidPrice(0.0);
    quote.setLastPrice(290.17);

    Position position = new Position();
    position.setAccountId(1);
    position.setTicker("MSFT");
    position.setPosition(10L);

    MarketOrderDto sellOrderDto = new MarketOrderDto();
    sellOrderDto.setAccountId(1);
    sellOrderDto.setSize(-10L);
    sellOrderDto.setTicker("MSFT");

    SecurityOrder securityOrderSell = new SecurityOrder();
    securityOrderSell.setAccountId(1);
    securityOrderSell.setStatus(Status.FILLED);
    securityOrderSell.setPrice(290.17);
    securityOrderSell.setTicker("MSFT");
    securityOrderSell.setSize(-10L);

    when(positionDao.findByIdAndTicker(any(), any())).thenReturn(Optional.of(position));

    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    when(quoteDao.findById(any())).thenReturn(Optional.of(quote));
    when(securityOrderDao.save(any())).thenReturn(securityOrderSell);

    SecurityOrder securityOrderResult = orderService.executeMarketOrder(sellOrderDto);
    verify(securityOrderDao).save(captorSecurityOrder.capture());

    assertEquals(securityOrderSell.getAccountId(), securityOrderResult.getAccountId());
    assertEquals(securityOrderSell.getTicker(), securityOrderResult.getTicker());
    assertEquals(securityOrderSell.getStatus(), securityOrderResult.getStatus());
    assertEquals(securityOrderSell.getPrice(), securityOrderResult.getPrice());
    assertEquals(securityOrderSell.getSize(), securityOrderResult.getSize());

    assertEquals(captorSecurityOrder.getValue().getAccountId(), securityOrderSell.getAccountId());
    assertEquals(captorSecurityOrder.getValue().getTicker(), securityOrderSell.getTicker());
    assertEquals(captorSecurityOrder.getValue().getStatus(), securityOrderSell.getStatus());
    assertEquals(captorSecurityOrder.getValue().getPrice(), securityOrderSell.getPrice());
    assertEquals(captorSecurityOrder.getValue().getSize(), securityOrderSell.getSize());
  }

    @After
  public void cleanUp() {
    accountDao.deleteAll();
    assertEquals(0, accountDao.count());
  }


}