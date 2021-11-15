package hoxtonr.frame.FTXFrame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FTXPosition {

    @JsonProperty("future")
    private String future;
    @JsonProperty("size")
    private Double size;
    @JsonProperty("side")
    private String side;
    @JsonProperty("netSize")
    private Double netSize;
    @JsonProperty("longOrderSize")
    private Double longOrderSize;
    @JsonProperty("shortOrderSize")
    private Double shortOrderSize;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("entryPrice")
    private Object entryPrice;
    @JsonProperty("unrealizedPnl")
    private Double unrealizedPnl;
    @JsonProperty("realizedPnl")
    private Double realizedPnl;
    @JsonProperty("initialMarginRequirement")
    private Double initialMarginRequirement;
    @JsonProperty("maintenanceMarginRequirement")
    private Double maintenanceMarginRequirement;

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getNetSize() {
        return netSize;
    }

    public void setNetSize(Double netSize) {
        this.netSize = netSize;
    }

    public Double getLongOrderSize() {
        return longOrderSize;
    }

    public void setLongOrderSize(Double longOrderSize) {
        this.longOrderSize = longOrderSize;
    }

    public Double getShortOrderSize() {
        return shortOrderSize;
    }

    public void setShortOrderSize(Double shortOrderSize) {
        this.shortOrderSize = shortOrderSize;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Object getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(Object entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Double getUnrealizedPnl() {
        return unrealizedPnl;
    }

    public void setUnrealizedPnl(Double unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public Double getRealizedPnl() {
        return realizedPnl;
    }

    public void setRealizedPnl(Double realizedPnl) {
        this.realizedPnl = realizedPnl;
    }

    public Double getInitialMarginRequirement() {
        return initialMarginRequirement;
    }

    public void setInitialMarginRequirement(Double initialMarginRequirement) {
        this.initialMarginRequirement = initialMarginRequirement;
    }

    public Double getMaintenanceMarginRequirement() {
        return maintenanceMarginRequirement;
    }

    public void setMaintenanceMarginRequirement(Double maintenanceMarginRequirement) {
        this.maintenanceMarginRequirement = maintenanceMarginRequirement;
    }

    public Double getOpenSize() {
        return openSize;
    }

    public void setOpenSize(Double openSize) {
        this.openSize = openSize;
    }

    public Double getCollateralUsed() {
        return collateralUsed;
    }

    public void setCollateralUsed(Double collateralUsed) {
        this.collateralUsed = collateralUsed;
    }

    public Object getEstimatedLiquidationPrice() {
        return estimatedLiquidationPrice;
    }

    public void setEstimatedLiquidationPrice(Object estimatedLiquidationPrice) {
        this.estimatedLiquidationPrice = estimatedLiquidationPrice;
    }

    @JsonProperty("openSize")
    private Double openSize;
    @JsonProperty("collateralUsed")
    private Double collateralUsed;
    @JsonProperty("estimatedLiquidationPrice")
    private Object estimatedLiquidationPrice;
}
