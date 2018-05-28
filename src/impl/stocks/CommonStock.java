package impl.stocks;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonStock extends Stock{

	public CommonStock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue) {
		super(stockSymbol, lastDivided, parValue);
	}

	@Override
	public BigDecimal calculateDividendYield(BigDecimal marketPrice) {
		return this.getLastDivident().divide(marketPrice, 2, RoundingMode.HALF_UP);
	}

}
