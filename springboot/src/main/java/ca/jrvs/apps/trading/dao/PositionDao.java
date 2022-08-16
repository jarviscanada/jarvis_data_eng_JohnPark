package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao extends JdbcCrudDao<Position> {

  private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);

  private final String TABLE_NAME = "position";
  private final String ID_COLUMN = "account_id";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }


  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  public String getTableName() {
    return TABLE_NAME;
  }

  public String getIdColumnName() {
    return ID_COLUMN;
  }

  Class<Position> getEntityClass() {
    return Position.class;
  }

  @Override
  public <S extends Position> S save(S position) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public int updateOne(Position entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(Integer id) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }


  @Override
  public <S extends Position> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Position position) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
