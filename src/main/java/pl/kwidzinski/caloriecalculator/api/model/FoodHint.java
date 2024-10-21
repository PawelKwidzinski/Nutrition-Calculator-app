
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
    "foodId",
    "label",
    "nutrients",
    "category",
    "categoryLabel",
    "image",
    "servingsPerContainer",
    "foodContentsLabel",
    "brand",
    "servingSizes"
})
@Generated("jsonschema2pojo")
public class FoodHint {

    @JsonProperty("foodId")
    private String foodId;
    @JsonProperty("label")
    private String label;
    @JsonProperty("nutrients")
    private NutrientsHint nutrients;
    @JsonProperty("category")
    private String category;
    @JsonProperty("categoryLabel")
    private String categoryLabel;
    @JsonProperty("image")
    private String image;
    @JsonProperty("servingsPerContainer")
    private Double servingsPerContainer;
    @JsonProperty("foodContentsLabel")
    private String foodContentsLabel;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("servingSizes")
    private List<ServingSize> servingSizes = null;
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
    public NutrientsHint getNutrients() {
        return nutrients;
    }

    @JsonProperty("nutrients")
    public void setNutrients(NutrientsHint nutrients) {
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

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("servingsPerContainer")
    public Double getServingsPerContainer() {
        return servingsPerContainer;
    }

    @JsonProperty("servingsPerContainer")
    public void setServingsPerContainer(Double servingsPerContainer) {
        this.servingsPerContainer = servingsPerContainer;
    }

    @JsonProperty("foodContentsLabel")
    public String getFoodContentsLabel() {
        return foodContentsLabel;
    }

    @JsonProperty("foodContentsLabel")
    public void setFoodContentsLabel(String foodContentsLabel) {
        this.foodContentsLabel = foodContentsLabel;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("servingSizes")
    public List<ServingSize> getServingSizes() {
        return servingSizes;
    }

    @JsonProperty("servingSizes")
    public void setServingSizes(List<ServingSize> servingSizes) {
        this.servingSizes = servingSizes;
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
