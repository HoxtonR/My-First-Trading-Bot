package hoxtonr.frame.FTXFrame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FTXBalance {

    @JsonProperty("coin")
    private String coin;
    @JsonProperty("total")
    private Double total;
    @JsonProperty("free")
    private Double free;
    @JsonProperty("availableWithoutBorrow")
    private Double availableWithoutBorrow;
    @JsonProperty("usdValue")
    private Double usdValue;
    @JsonProperty("spotBorrow")
    private Double spotBorrow;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
    }

    public Double getAvailableWithoutBorrow() {
        return availableWithoutBorrow;
    }

    public void setAvailableWithoutBorrow(Double availableWithoutBorrow) {
        this.availableWithoutBorrow = availableWithoutBorrow;
    }

    public Double getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(Double usdValue) {
        this.usdValue = usdValue;
    }

    public Double getSpotBorrow() {
        return spotBorrow;
    }

    public void setSpotBorrow(Double spotBorrow) {
        this.spotBorrow = spotBorrow;
    }
}
