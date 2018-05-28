package impl.stocks;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import api.IMarket;

public class Market implements IMarket{
	
	private HashMap<String,Stock> stocks;

	public Market(){
		this.stocks = new HashMap<String,Stock>();
	}

	@Override
	public double calculateGBCEAllShareIndex() {
		BigDecimal sumPrice = new BigDecimal(1);
		List<Trade> trades;
		
		Iterator<AbstractMap.Entry<String,Stock>> it = this.stocks.entrySet().iterator();
		while (it.hasNext()){
			AbstractMap.Entry<String,Stock> pair = it.next();
		    trades = pair.getValue().getTrades();
			for (int j = 0; j < trades.size(); j++){
				sumPrice = sumPrice.multiply(trades.get(j).getTradePrice());
			}
		}
		return Math.sqrt(sumPrice.doubleValue());
	}
	
	@Override
	public int recordTrade(String stockSymbol, BigDecimal numberOfShares, boolean isBuy, BigDecimal tradePrice) {
		Stock stock = stocks.get(stockSymbol);
		return stock.recordTrade(new Trade(numberOfShares, isBuy, tradePrice));
	}
	
	@Override
	public void addStock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue) {
		this.stocks.put(stockSymbol, new CommonStock(stockSymbol, lastDivided, parValue));
	}

	@Override
	public void addStock(String stockSymbol, BigDecimal lastDivided, BigDecimal parValue, BigDecimal fixedDividentPercent) {
		this.stocks.put(stockSymbol, new PreferredStock(stockSymbol, lastDivided, parValue, fixedDividentPercent));
	}
	
	@Override
	public Stock selectStock(String stockSymbol) {
		return this.stocks.get(stockSymbol);
	}

	public AbstractMap<String,Stock> getStocks() {
		return stocks;
	}

	public void setStocks(HashMap<String,Stock> stocks) {
		this.stocks = stocks;
	}
	
}
