package api;

import java.math.BigDecimal;

import impl.stocks.Trade;

public interface IStock {
	
	public BigDecimal calculateDividendYield(BigDecimal marketPrice); 
	
	public BigDecimal calculatePERatio(BigDecimal marketPrice);
	
	public int recordTrade(Trade trade);
	
	public BigDecimal calculateWeightedStockPrice();

}
