package hoxtonr.project.index.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class JinShiNewsModel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("time")
    private String time;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("important")
    private Integer important;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Integer getImportant() {
        return important;
    }

    public void setImportant(Integer important) {
        this.important = important;
    }

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("pic")
        private String pic;
        @JsonProperty("content")
        private String content;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
