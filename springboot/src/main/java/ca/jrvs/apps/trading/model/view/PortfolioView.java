package ca.jrvs.apps.trading.model.view;

import ca.jrvs.apps.trading.model.domain.SecurityRow;
import java.util.ArrayList;
import java.util.List;

public class PortfolioView {

  private List<SecurityRow> securityRows = new ArrayList<>();

  public List<SecurityRow> getSecurityRows() {
    return securityRows;
  }

  public void setSecurityRows(List<SecurityRow> securityRows) {
    this.securityRows = securityRows;
  }

  public void addSecurityRow(SecurityRow securityRow) {
    securityRows.add(securityRow);
  }

}
