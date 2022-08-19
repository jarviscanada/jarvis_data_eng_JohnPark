package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private QuoteDao quoteDao;
  private PositionDao positionDao;

  @Autowired
  public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao,
      PositionDao positionDao) {
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.quoteDao = quoteDao;
    this.positionDao = positionDao;
  }

  /**
   * Execute a market order
   * <p>
   * - validate the order (e.g. size, and ticker)
   * - Create a securityOrder (for security_order table)
   * - Handle buy or sell order
   *  - buy order : check account balance (calls helper method)
   *  - sell order : check position for ticker/symbol (calls helper method)
   *  - (please don't forget to update securityOrder.status)
   * - Save and return securityOrder
   * <p>
   * NOTE: you will need to some helper methods (protected or private)
   *
   * @param orderDto market order
   * @return SecurityOrder from seucurity_order table
   * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
   * @throws IllegalArgumentException                    for invalid input
   */
  public SecurityOrder executeMarketOrder(MarketOrderDto orderDto) {
    // Validate size
    if (orderDto.getSize() == 0) {
      throw new IllegalArgumentException("Invalid size");
    }

    // Validate account
    Optional<Account> optionalAccount = accountDao.findById(orderDto.getAccountId());
    if (!optionalAccount.isPresent()) {
      throw new IllegalArgumentException("The account_id does not exist: " + orderDto.getAccountId());
    }
    Account account = optionalAccount.get();

    SecurityOrder securityOrder = new SecurityOrder();

    if (orderDto.getSize() > 0) {
      handleBuyMarketOrder(orderDto, securityOrder, account);
    } else {
      handleSellMarketOrder(orderDto, securityOrder, account);
    }

    return securityOrderDao.save(securityOrder);
  }

  /**
   * Helper method that execute a buy order
   *
   * @param marketOrderDto user order
   * @param securityOrder  to be saved in data database
   * @param account        account
   */
  protected void handleBuyMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
      Account account) {

    String ticker = marketOrderDto.getTicker();
    Quote quote = validateTickerAndGetQuote(ticker);

    Double price = quote.getLastPrice();

    if (account.getAmount() < marketOrderDto.getSize() * price) {
      setSecurityOrder(securityOrder, marketOrderDto, price, Status.CANCELLED);
      securityOrder.setNotes("Insufficient fund");
    } else {
      setSecurityOrder(securityOrder, marketOrderDto, price, Status.FILLED);
      account.setAmount(account.getAmount() - marketOrderDto.getSize() * price);
      accountDao.save(account);
    }
  }

  /**
   * Helper method that execute a sell order
   *
   * @param marketOrderDto user order
   * @param securityOrder  to be saved in data database
   * @param account        account
   */
  protected void handleSellMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
      Account account) {
    String ticker = marketOrderDto.getTicker();
    Integer accountId = marketOrderDto.getAccountId();
    Quote quote  = validateTickerAndGetQuote(ticker);
    Double price = quote.getLastPrice();
    Optional<Position> optionalPosition = positionDao.findByIdAndTicker(accountId, ticker);

    if (!optionalPosition.isPresent()) {
      throw new IllegalArgumentException("The trader do not possess the ticker to sell");
    }

    Position position = optionalPosition.get();
    if (position.getPosition() < marketOrderDto.getSize()) {
      setSecurityOrder(securityOrder, marketOrderDto, price, Status.CANCELLED);
      securityOrder.setNotes("Insufficient size available: " + position.getPosition());
    } else {
      setSecurityOrder(securityOrder, marketOrderDto, price, Status.FILLED);
      account.setAmount(account.getAmount() +  marketOrderDto.getSize() * price);
      accountDao.save(account);
    }
  }

  /**
   * Helper method to validate ticker
   */
  private Quote validateTickerAndGetQuote(String ticker) {
    Optional<Quote> optionalQuote = quoteDao.findById(ticker);
    if (!optionalQuote.isPresent()) {
      throw new IllegalArgumentException("Ticker not available: " + ticker);
    }
    return optionalQuote.get();
  }

  /**
   * Helper method to set securityOrder
   */
  private void setSecurityOrder(SecurityOrder securityOrder, MarketOrderDto marketOrderDto,
      Double price, Status status) {
    securityOrder.setAccountId(marketOrderDto.getAccountId());
    securityOrder.setTicker(marketOrderDto.getTicker());
    securityOrder.setSize(marketOrderDto.getSize());
    securityOrder.setPrice(price);
    securityOrder.setStatus(status);
  }

}
