package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.List;
import java.util.Optional;
import javafx.geometry.Pos;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

  private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);

  private final String TABLE_NAME = "position";
  private final String ID_COLUMN = "account_id";
  private final String TICKER_COLUMN = "ticker";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  public List<Position> findByAccountId(Integer accountId) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " =?";
    return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Position.class), accountId);
  }

  public List<Position> findByTicker(String ticker) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + TICKER_COLUMN + " =?";
    return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Position.class), ticker);
  }

  public Optional<Position> findByIdAndTicker(Integer accountId, String ticker) {
    Optional<Position> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " =? AND " +
        TICKER_COLUMN + " =?";

    try {
      entity = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Position.class), accountId, ticker));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find account_id " + accountId + "and ticker " + ticker, e);
    }

    return entity;
  }

  public List<Position> findAll() {
    String selectAllSql = "SELECT * FROM " + TABLE_NAME;
    return jdbcTemplate.query(selectAllSql, new BeanPropertyRowMapper<>(Position.class));
  }

}
