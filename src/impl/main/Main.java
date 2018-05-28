package impl.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import impl.manager.StockMarketManager;
import impl.stocks.CommonStock;
import impl.stocks.Market;
import impl.stocks.PreferredStock;
import impl.stocks.Stock;

public class Main {
	
	
	public static void main(String[] args){
		
		Market market = new Market();
		market.addStock("TEA", new BigDecimal(0), new BigDecimal(100));
		market.addStock("POP", new BigDecimal(8), new BigDecimal(100));
		market.addStock("ALE", new BigDecimal(23), new BigDecimal(60));
		market.addStock("JOE", new BigDecimal(13), new BigDecimal(250));
		market.addStock("GIN", new BigDecimal(8), new BigDecimal(100), new BigDecimal(2));
		
		StockMarketManager smm = new StockMarketManager(market);
		smm.runMarketManager();
	}
}
