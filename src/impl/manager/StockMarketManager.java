package impl.manager;

import java.math.BigDecimal;
import java.util.Scanner;

import api.IStockMarketManager;
import impl.stocks.Market;
import impl.stocks.Stock;
import impl.stocks.Trade;

public class StockMarketManager implements IStockMarketManager{
	
	private Market market;
	
	public StockMarketManager(Market market){
		this.market = market;
	}

	@Override
	public void runMarketManager() {
		Scanner scanner = new Scanner(System.in);
		String command = "";
		boolean keepReading = true;
		while (keepReading){
			System.out.println("Please enter a command: STOCK / GBCE / EXIT");
			command = scanner.nextLine();
			if (command.equals("STOCK")){
				this.processStockCommand(scanner);
			}
			else if (command.equals("GBCE")){
				System.out.println("GBCE All Share Index: " + market.calculateGBCEAllShareIndex());			
			}
			else if (command.equals("EXIT")){
				keepReading = false;
			}
			else {
				System.out.println("Error, please try again.");
			}
		}
		
		scanner.close();
		
	}
	
	private void processStockCommand(Scanner scanner){
		System.out.println("Enter a stock symbol:");
		String input = scanner.nextLine();
		Stock selectedStock = this.market.selectStock(input);
		if (selectedStock != null){
			System.out.println("Please enter a command: YIELD / PERATIO / TRADE / VWSP");
			input = scanner.nextLine();
			if (input.equals("YIELD")){
				System.out.println("Enter a market price:");
				BigDecimal priceInput = scanner.nextBigDecimal();
				System.out.println("Dividend Yield: " + selectedStock.calculateDividendYield(priceInput));
			}
			else if (input.equals("PERATIO")){
				System.out.println("Enter a market price:");
				BigDecimal priceInput = scanner.nextBigDecimal();
				System.out.println("P/E Ratio: " + selectedStock.calculatePERatio(priceInput));
			}
			else if (input.equals("TRADE")){
				int result = selectedStock.recordTrade(this.processTradeCommand(scanner));
				if (result == 0){
					System.out.println("Trade successful.");
				}
				else {
					System.out.println("Trade unsuccessful.");
				}
			}
			else if (input.equals("VWSP")){
				System.out.println(selectedStock.calculateWeightedStockPrice());
			}
		}
		else {
			System.out.println("Error looking up stock.");
		}
	}
	
	private Trade processTradeCommand(Scanner scanner){
		System.out.println("Enter trade type: BUY/SELL");
		boolean isBuy;
		String input = scanner.nextLine();
		if (input.equals("BUY")){
			isBuy = true;
		}
		else {
			isBuy = false;
		}
		
		System.out.println("Enter number of shares:");
		BigDecimal numberOfShares = scanner.nextBigDecimal();
		
		System.out.println("Enter a trade price");
		BigDecimal tradePrice = scanner.nextBigDecimal();
		
		return new Trade(numberOfShares, isBuy, tradePrice);
	}

}
