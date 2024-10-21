
package pl.kwidzinski.caloriecalculator.api.model;

import java.util.HashMap;
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
    "ENERC_KCAL",
    "PROCNT",
    "FAT",
    "CHOCDF",
    "FIBTG"
})
@Generated("jsonschema2pojo")
public class NutrientsHint {

    @JsonProperty("ENERC_KCAL")
    private Double enercKcal;
    @JsonProperty("PROCNT")
    private Double procnt;
    @JsonProperty("FAT")
    private Double fat;
    @JsonProperty("CHOCDF")
    private Double chocdf;
    @JsonProperty("FIBTG")
    private Double fibtg;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ENERC_KCAL")
    public Double getEnercKcal() {
        return enercKcal;
    }

    @JsonProperty("ENERC_KCAL")
    public void setEnercKcal(Double enercKcal) {
        this.enercKcal = enercKcal;
    }

    @JsonProperty("PROCNT")
    public Double getProcnt() {
        return procnt;
    }

    @JsonProperty("PROCNT")
    public void setProcnt(Double procnt) {
        this.procnt = procnt;
    }

    @JsonProperty("FAT")
    public Double getFat() {
        return fat;
    }

    @JsonProperty("FAT")
    public void setFat(Double fat) {
        this.fat = fat;
    }

    @JsonProperty("CHOCDF")
    public Double getChocdf() {
        return chocdf;
    }

    @JsonProperty("CHOCDF")
    public void setChocdf(Double chocdf) {
        this.chocdf = chocdf;
    }

    @JsonProperty("FIBTG")
    public Double getFibtg() {
        return fibtg;
    }

    @JsonProperty("FIBTG")
    public void setFibtg(Double fibtg) {
        this.fibtg = fibtg;
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
