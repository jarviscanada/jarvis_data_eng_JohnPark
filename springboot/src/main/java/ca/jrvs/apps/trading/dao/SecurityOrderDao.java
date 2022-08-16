package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

  private static final Logger logger = LoggerFactory.getLogger(SecurityOrderDao.class);

  private final String TABLE_NAME = "security_order";
  private final String ID_COLUMN = "id";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }


  @Override
  public int updateOne(SecurityOrder securityOrder) {
    String updateSql = "UPDATE " + getTableName()
        + " SET account_id=?, status=?, ticker=?, size=?, price=?, notes=? WHERE "
        + getIdColumnName() + " =?";
    return jdbcTemplate.update(updateSql, makeUpdateValues(securityOrder));
  }

  private Object[] makeUpdateValues(SecurityOrder securityOrder) {
    return new Object[]{securityOrder.getAccountId(), securityOrder.getStatus(),
        securityOrder.getTicker(), securityOrder.getSize(), securityOrder.getPrice(),
        securityOrder.getNotes(), securityOrder.getId()};
  }

  private Object[] makeAddValues(SecurityOrder securityOrder) {
    return new Object[]{securityOrder.getId(), securityOrder.getAccountId(),
        securityOrder.getStatus(), securityOrder.getTicker(), securityOrder.getSize(),
        securityOrder.getPrice(), securityOrder.getNotes()};
  }

  @Override
  public <S extends SecurityOrder> Iterable<S> saveAll(Iterable<S> securityOrders) {
    String updateSql = "UPDATE " + getTableName()
        + " SET account_id=?, status=?, ticker=?, size=?, price=?, notes=? WHERE "
        + getIdColumnName() + " =?";
    String insertSql = "INSERT INTO " + getTableName() + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    List<Object[]> batchUpdate = new ArrayList<>();
    List<Object[]> batchAdd = new ArrayList<>();

    securityOrders.forEach(securityOrder -> {
      if (!existsById(securityOrder.getId())) {
        batchAdd.add(makeAddValues(securityOrder));
      } else {
        batchUpdate.add(makeUpdateValues(securityOrder));
      }
    });

    int[] updateRows = getJdbcTemplate().batchUpdate(updateSql, batchUpdate);
    int[] insertRows = jdbcTemplate.batchUpdate(insertSql, batchAdd);
    int totalRow = Arrays.stream(updateRows).sum() + Arrays.stream(insertRows).sum();

    if (totalRow != Iterables.size(securityOrders)) {
      throw new IncorrectResultSizeDataAccessException("Number of rows ",
          Iterables.size(securityOrders),
          totalRow);
    }

    return StreamSupport.stream(securityOrders.spliterator(), false)
        .collect(Collectors.toList());


  }

  @Override
  public void delete(SecurityOrder securityOrder) {
    throw new UnsupportedOperationException("Not implemented");

  }
}
