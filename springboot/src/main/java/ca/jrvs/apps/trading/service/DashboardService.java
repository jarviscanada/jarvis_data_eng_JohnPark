package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityRow;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.PortfolioView;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.List;
import java.util.Optional;
import javax.sound.sampled.Port;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DashboardService {

  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired
  public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
      QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create and return a traderAccountView by trader ID
   * - get trader account by id
   * - get trader info by id
   * - create and return a traderAccountView
   *
   * @param traderId must not be null
   * @return traderAccountView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public TraderAccountView getTraderAccount(Integer traderId) {
    Trader trader = findTraderById(traderId);
    Account account = findAccountByTraderId(traderId);

    return new TraderAccountView(trader, account);
  }

  /**
   * Create and return portfolioView by trader ID
   * - get account by trader id
   * - get positions by account id
   * - create and return a portfolioView
   *
   * @param traderId must not be null
   * @return portfolioView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public PortfolioView getProfileViewByTraderId(Integer traderId) {
    Account account = findAccountByTraderId(traderId);
    List<Position> positions = positionDao.findByAccountId(account.getId());
    PortfolioView portfolioView = new PortfolioView();

    positions.forEach(position -> {
      String ticker = position.getTicker();
      Quote quote = quoteDao.findById(ticker).orElseThrow(() -> new IllegalStateException("Invalid ticker detected"));
      SecurityRow securityRow = new SecurityRow(position, quote, ticker);
      portfolioView.addSecurityRow(securityRow);
    });
    return portfolioView;
  }

  /**
   * @throws IllegalArgumentException if traderId is not found
   */
  private Trader findTraderById(Integer traderId) {
    return traderDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }

  /**
   * @throws IllegalArgumentException if traderId is not found
   */
  private Account findAccountByTraderId(Integer traderId) {
    return accountDao.findByTraderId(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }

}
