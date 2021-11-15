package hoxtonr.frame.BinanceFrame.market.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NextFundingRate {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("markPrice")
    private String markPrice;
    @JsonProperty("indexPrice")
    private String indexPrice;
    @JsonProperty("estimatedSettlePrice")
    private String estimatedSettlePrice;
    @JsonProperty("lastFundingRate")
    private String lastFundingRate;
    @JsonProperty("nextFundingTime")
    private Long nextFundingTime;
    @JsonProperty("interestRate")
    private String interestRate;
    @JsonProperty("time")
    private Long time;
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(String markPrice) {
        this.markPrice = markPrice;
    }

    public String getIndexPrice() {
        return indexPrice;
    }

    public void setIndexPrice(String indexPrice) {
        this.indexPrice = indexPrice;
    }

    public String getEstimatedSettlePrice() {
        return estimatedSettlePrice;
    }

    public void setEstimatedSettlePrice(String estimatedSettlePrice) {
        this.estimatedSettlePrice = estimatedSettlePrice;
    }

    public String getLastFundingRate() {
        return lastFundingRate;
    }

    public void setLastFundingRate(String lastFundingRate) {
        this.lastFundingRate = lastFundingRate;
    }

    public Long getNextFundingTime() {
        return nextFundingTime;
    }

    public void setNextFundingTime(Long nextFundingTime) {
        this.nextFundingTime = nextFundingTime;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return symbol+"的下一次费率为: "+lastFundingRate+"\n";
    }
}
