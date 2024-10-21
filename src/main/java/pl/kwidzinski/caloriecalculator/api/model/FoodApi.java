
package pl.kwidzinski.caloriecalculator.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "parsed",
    "hints",
    "_links"
})
@Generated("jsonschema2pojo")
public class FoodApi {

    @JsonProperty("text")
    private String text;
    @JsonProperty("parsed")
    private List<Parsed> parsed = null;
    @JsonProperty("hints")
    private List<Hint> hints = null;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("parsed")
    public List<Parsed> getParsed() {
        return parsed;
    }

    @JsonProperty("parsed")
    public void setParsed(List<Parsed> parsed) {
        this.parsed = parsed;
    }

    @JsonProperty("hints")
    public List<Hint> getHints() {
        return hints;
    }

    @JsonProperty("hints")
    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
