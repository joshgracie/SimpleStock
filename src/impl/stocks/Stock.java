package impl.stocks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import api.IStock;

public abstract class Stock implements IStock {
	
	private String stockSymbol;
	private BigDecimal lastDividend;
	private BigDecimal parValue;
	private List<Trade> trades;
	
	public Stock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue){
		this.stockSymbol = stockSymbol;
		this.lastDividend = lastDivided;
		this.parValue = parValue;
		this.trades = new ArrayList<Trade>();
	}

	@Override
	public abstract BigDecimal calculateDividendYield(BigDecimal marketPrice);

	@Override
	public BigDecimal calculatePERatio(BigDecimal marketPrice) {
		return marketPrice.divide(lastDividend, 2, RoundingMode.HALF_UP);
	}

	@Override
	public int recordTrade(Trade trade) {
		if (this.trades.add(trade)){
			return 0;	// SUCCESS
		}
		else {
			return -1;	// FAILURE
		}
	}

	@Override
	public BigDecimal calculateWeightedStockPrice() {
		if (this.trades.size() > 0 ){
			BigDecimal totalPrice = new BigDecimal(0);
			BigDecimal totalNumberOfShares = new BigDecimal(0);
			
			Date cutoffDate = new Date (System.currentTimeMillis() - 15 * 1000);
			
			int i = this.trades.size()-1;
			Trade currentTrade = this.trades.get(i);
			
			// Calculate total price by multiplying each trade price by quantity and summing
			// Also calculate total quantity, but summing quantity of each trade
			while (currentTrade.getTradeTime().before(cutoffDate) && i >= 0){
				totalPrice = totalPrice.add(currentTrade.getTradePrice().multiply(currentTrade.getNumberOfShares()));
				totalNumberOfShares = totalNumberOfShares.add(currentTrade.getNumberOfShares());
				
				currentTrade = this.trades.get(i--);
			}
			
			return totalPrice.divide(totalNumberOfShares, 2, RoundingMode.HALF_UP);
		}
		return new BigDecimal(0);
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public BigDecimal getLastDivident() {
		return lastDividend;
	}

	public void setLastDivident(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
	 
	

}
