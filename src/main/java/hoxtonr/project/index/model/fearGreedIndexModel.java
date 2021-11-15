package hoxtonr.project.index.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class fearGreedIndexModel {


    @JsonProperty("value")
    private String value;
    @JsonProperty("value_classification")
    private String valueClassification;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("time_until_update")
    private String timeUntilUpdate;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueClassification() {
        return valueClassification;
    }

    public void setValueClassification(String valueClassification) {
        this.valueClassification = valueClassification;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimeUntilUpdate() {
        return timeUntilUpdate;
    }

    public void setTimeUntilUpdate(String timeUntilUpdate) {
        this.timeUntilUpdate = timeUntilUpdate;
    }
}
