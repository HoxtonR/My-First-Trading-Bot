package hoxtonr.frame.FTXFrame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FTXMarket {

    @JsonProperty("name")
    private String name;
    @JsonProperty("underlying")
    private String underlying;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;
    @JsonProperty("expiry")
    private Object expiry;
    @JsonProperty("perpetual")
    private Boolean perpetual;
    @JsonProperty("expired")
    private Boolean expired;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("postOnly")
    private Boolean postOnly;
    @JsonProperty("priceIncrement")
    private Double priceIncrement;
    @JsonProperty("sizeIncrement")
    private Double sizeIncrement;
    @JsonProperty("last")
    private Double last;
    @JsonProperty("bid")
    private Double bid;
    @JsonProperty("ask")
    private Double ask;
    @JsonProperty("index")
    private Double index;
    @JsonProperty("mark")
    private Double mark;
    @JsonProperty("imfFactor")
    private Double imfFactor;
    @JsonProperty("lowerBound")
    private Double lowerBound;
    @JsonProperty("upperBound")
    private Double upperBound;
    @JsonProperty("underlyingDescription")
    private String underlyingDescription;
    @JsonProperty("expiryDescription")
    private String expiryDescription;
    @JsonProperty("moveStart")
    private Object moveStart;
    @JsonProperty("marginPrice")
    private Double marginPrice;
    @JsonProperty("positionLimitWeight")
    private Double positionLimitWeight;
    @JsonProperty("group")
    private String group;
    @JsonProperty("change1h")
    private Double change1h;
    @JsonProperty("change24h")
    private Double change24h;
    @JsonProperty("changeBod")
    private Double changeBod;
    @JsonProperty("volumeUsd24h")
    private Double volumeUsd24h;
    @JsonProperty("volume")
    private Double volume;
    @JsonProperty("openInterest")
    private Double openInterest;
    @JsonProperty("openInterestUsd")
    private Double openInterestUsd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getExpiry() {
        return expiry;
    }

    public void setExpiry(Object expiry) {
        this.expiry = expiry;
    }

    public Boolean getPerpetual() {
        return perpetual;
    }

    public void setPerpetual(Boolean perpetual) {
        this.perpetual = perpetual;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public void setPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
    }

    public Double getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(Double priceIncrement) {
        this.priceIncrement = priceIncrement;
    }

    public Double getSizeIncrement() {
        return sizeIncrement;
    }

    public void setSizeIncrement(Double sizeIncrement) {
        this.sizeIncrement = sizeIncrement;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Double getImfFactor() {
        return imfFactor;
    }

    public void setImfFactor(Double imfFactor) {
        this.imfFactor = imfFactor;
    }

    public Double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public String getUnderlyingDescription() {
        return underlyingDescription;
    }

    public void setUnderlyingDescription(String underlyingDescription) {
        this.underlyingDescription = underlyingDescription;
    }

    public String getExpiryDescription() {
        return expiryDescription;
    }

    public void setExpiryDescription(String expiryDescription) {
        this.expiryDescription = expiryDescription;
    }

    public Object getMoveStart() {
        return moveStart;
    }

    public void setMoveStart(Object moveStart) {
        this.moveStart = moveStart;
    }

    public Double getMarginPrice() {
        return marginPrice;
    }

    public void setMarginPrice(Double marginPrice) {
        this.marginPrice = marginPrice;
    }

    public Double getPositionLimitWeight() {
        return positionLimitWeight;
    }

    public void setPositionLimitWeight(Double positionLimitWeight) {
        this.positionLimitWeight = positionLimitWeight;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Double getChange1h() {
        return change1h;
    }

    public void setChange1h(Double change1h) {
        this.change1h = change1h;
    }

    public Double getChange24h() {
        return change24h;
    }

    public void setChange24h(Double change24h) {
        this.change24h = change24h;
    }

    public Double getChangeBod() {
        return changeBod;
    }

    public void setChangeBod(Double changeBod) {
        this.changeBod = changeBod;
    }

    public Double getVolumeUsd24h() {
        return volumeUsd24h;
    }

    public void setVolumeUsd24h(Double volumeUsd24h) {
        this.volumeUsd24h = volumeUsd24h;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(Double openInterest) {
        this.openInterest = openInterest;
    }

    public Double getOpenInterestUsd() {
        return openInterestUsd;
    }

    public void setOpenInterestUsd(Double openInterestUsd) {
        this.openInterestUsd = openInterestUsd;
    }
}
