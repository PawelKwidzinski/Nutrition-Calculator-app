
package pl.kwidzinski.caloriecalculator.api.model;

import java.util.HashMap;
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
    "foodId",
    "label",
    "nutrients",
    "category",
    "categoryLabel"
})
@Generated("jsonschema2pojo")
public class Food {

    @JsonProperty("foodId")
    private String foodId;
    @JsonProperty("label")
    private String label;
    @JsonProperty("nutrients")
    private Nutrients nutrients;
    @JsonProperty("category")
    private String category;
    @JsonProperty("categoryLabel")
    private String categoryLabel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("foodId")
    public String getFoodId() {
        return foodId;
    }

    @JsonProperty("foodId")
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("nutrients")
    public Nutrients getNutrients() {
        return nutrients;
    }

    @JsonProperty("nutrients")
    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("categoryLabel")
    public String getCategoryLabel() {
        return categoryLabel;
    }

    @JsonProperty("categoryLabel")
    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
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
