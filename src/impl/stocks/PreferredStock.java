package impl.stocks;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PreferredStock extends Stock{
	
	private BigDecimal fixedDividendPercent;

	public PreferredStock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue, BigDecimal fixedDividentPercent) {
		super(stockSymbol, lastDivided, parValue);
		this.fixedDividendPercent = fixedDividentPercent;
	}

	@Override
	public BigDecimal calculateDividendYield(BigDecimal marketPrice) {
		BigDecimal value = this.getParValue().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		value = value.multiply(this.fixedDividendPercent);
		value = value.divide(marketPrice);
		return value;
	}

}
