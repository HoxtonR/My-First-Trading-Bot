package hoxtonr.frame.FTXFrame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FTXNextFundingRate {

    @JsonProperty("volume")
    private Double volume;
    @JsonProperty("nextFundingRate")
    private Double nextFundingRate;
    @JsonProperty("nextFundingTime")
    private String nextFundingTime;
    @JsonProperty("expirationPrice")
    private Double expirationPrice;
    @JsonProperty("predictedExpirationPrice")
    private Double predictedExpirationPrice;
    @JsonProperty("strikePrice")
    private Double strikePrice;
    @JsonProperty("openInterest")
    private Double openInterest;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getNextFundingRate() {
        return nextFundingRate;
    }

    public void setNextFundingRate(Double nextFundingRate) {
        this.nextFundingRate = nextFundingRate;
    }

    public String getNextFundingTime() {
        return nextFundingTime;
    }

    public void setNextFundingTime(String nextFundingTime) {
        this.nextFundingTime = nextFundingTime;
    }

    public Double getExpirationPrice() {
        return expirationPrice;
    }

    public void setExpirationPrice(Double expirationPrice) {
        this.expirationPrice = expirationPrice;
    }

    public Double getPredictedExpirationPrice() {
        return predictedExpirationPrice;
    }

    public void setPredictedExpirationPrice(Double predictedExpirationPrice) {
        this.predictedExpirationPrice = predictedExpirationPrice;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Double getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(Double openInterest) {
        this.openInterest = openInterest;
    }
}
