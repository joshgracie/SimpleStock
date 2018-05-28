package api;

import java.math.BigDecimal;

import impl.stocks.Stock;

public interface IMarket {
	
	public double calculateGBCEAllShareIndex();
	
	public int recordTrade(String stockSymbol, BigDecimal numberOfShares, boolean isBuy, BigDecimal tradePrice);
	
	public void addStock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue);
	
	public void addStock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue, BigDecimal fixedDividentPercent);
	
	public Stock selectStock(String stockSymbol);

}
