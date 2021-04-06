
package pl.kwidzinski.caloriecalculator.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "food",
    "measures"
})
@Generated("jsonschema2pojo")
public class Hint {

    @JsonProperty("food")
    private FoodHint food;
    @JsonProperty("measures")
    private List<Measure> measures = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("food")
    public FoodHint getFood() {
        return food;
    }

    @JsonProperty("food")
    public void setFood(FoodHint food) {
        this.food = food;
    }

    @JsonProperty("measures")
    public List<Measure> getMeasures() {
        return measures;
    }

    @JsonProperty("measures")
    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
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
