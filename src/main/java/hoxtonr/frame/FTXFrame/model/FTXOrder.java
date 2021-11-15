package hoxtonr.frame.FTXFrame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FTXOrder {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("clientId")
    private Object clientId;
    @JsonProperty("market")
    private String market;
    @JsonProperty("type")
    private String type;
    @JsonProperty("side")
    private String side;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("size")
    private Double size;
    @JsonProperty("status")
    private String status;
    @JsonProperty("filledSize")
    private Double filledSize;
    @JsonProperty("remainingSize")
    private Double remainingSize;
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;
    @JsonProperty("liquidation")
    private Object liquidation;
    @JsonProperty("avgFillPrice")
    private Object avgFillPrice;
    @JsonProperty("postOnly")
    private Boolean postOnly;
    @JsonProperty("ioc")
    private Boolean ioc;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("future")
    private String future;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getClientId() {
        return clientId;
    }

    public void setClientId(Object clientId) {
        this.clientId = clientId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFilledSize() {
        return filledSize;
    }

    public void setFilledSize(Double filledSize) {
        this.filledSize = filledSize;
    }

    public Double getRemainingSize() {
        return remainingSize;
    }

    public void setRemainingSize(Double remainingSize) {
        this.remainingSize = remainingSize;
    }

    public Boolean getReduceOnly() {
        return reduceOnly;
    }

    public void setReduceOnly(Boolean reduceOnly) {
        this.reduceOnly = reduceOnly;
    }

    public Object getLiquidation() {
        return liquidation;
    }

    public void setLiquidation(Object liquidation) {
        this.liquidation = liquidation;
    }

    public Object getAvgFillPrice() {
        return avgFillPrice;
    }

    public void setAvgFillPrice(Object avgFillPrice) {
        this.avgFillPrice = avgFillPrice;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public void setPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
    }

    public Boolean getIoc() {
        return ioc;
    }

    public void setIoc(Boolean ioc) {
        this.ioc = ioc;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }
    @Override
    public String toString() {
        String mess = "";
        mess += "Successfully post order:";
        mess += "{";
        mess += "FTXOrderInformation:";
        mess += "{";
        mess += "Market:" + getMarket();
        mess += ", Id = " + getId();
        mess += ", Price = " + getPrice();
        mess += ", LiquidationPrice = " + getLiquidation();
        mess += ", Remainming Size = " + getRemainingSize();
        mess += "}";
        mess += "}";
        return mess;
    }
}
