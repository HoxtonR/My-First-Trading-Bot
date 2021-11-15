package hoxtonr.project.HighRiskProject.BinanceAnnounceGrabber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnnounceModel {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("code")
    private String code;
    @JsonProperty("title")
    private String title;
    @JsonProperty("catalogId")
    private Integer catalogId;
    @JsonProperty("language")
    private String language;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("cachedAt")
    private Integer cachedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getCachedAt() {
        return cachedAt;
    }

    public void setCachedAt(Integer cachedAt) {
        this.cachedAt = cachedAt;
    }
}
