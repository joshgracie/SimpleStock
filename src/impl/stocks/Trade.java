package impl.stocks;

import java.math.BigDecimal;
import java.util.Date;

import api.ITrade;

public class Trade implements ITrade{
	
	private BigDecimal numberOfShares;
	private boolean isBuy;
	private BigDecimal tradePrice;
	private Date tradeTime;
	
	public Trade(BigDecimal numberOfShares, boolean isBuy, BigDecimal tradePrice){
		this.numberOfShares = numberOfShares;
		this.isBuy = isBuy;
		this.tradePrice = tradePrice;
		this.tradeTime = new Date(System.currentTimeMillis());
	}

	public BigDecimal getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(BigDecimal numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	

}
