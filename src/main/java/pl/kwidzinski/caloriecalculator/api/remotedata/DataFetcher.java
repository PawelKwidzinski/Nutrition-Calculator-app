package pl.kwidzinski.caloriecalculator.api.remotedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kwidzinski.caloriecalculator.api.model.FoodApi;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.util.DataParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataFetcher {

    @Value("${base_url}")
    private String baseUrl;
    @Value("${app_id_param}")
    private String appIdParam;
    @Value("${app_id}")
    private String appId;
    @Value("${app_key_param}")
    private String appKeyParam;
    @Value("${app_key}")
    private String appKey;
    @Value("${ingr_param}")
    private String ingr;

    private final Logger logger = LoggerFactory.getLogger(DataFetcher.class);

    private final DataParser dataParser;

    public DataFetcher(final DataParser dataParser) {
        this.dataParser = dataParser;
    }

    public List<Ingredient> fetchDataFromApi(String ingredient, Integer weight) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam(appIdParam, appId)
                .queryParam(appKeyParam, appKey)
                .queryParam(ingr, ingredient);

        String uri = builder.toUriString();

        try {
            RestTemplate restTemplate = new RestTemplate();
            Optional<FoodApi> apiOptional = Optional.ofNullable(restTemplate.getForObject(uri, FoodApi.class));
            return mapIngredients(apiOptional, weight);
        } catch (RestClientException e) {
            logger.error("Error during fetching from remote API", e);
        }
        return new ArrayList<>();
    }

    private List<Ingredient> mapIngredients(Optional<FoodApi> foodApi, int weight) {
        return foodApi.map(api -> api.getHints().stream()
                .map(element -> {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setFoodId(element.getFood().getFoodId());
                    ingredient.setName(element.getFood().getLabel());
                    ingredient.setImageUrl(element.getFood().getImage());
                    ingredient.setUnit("g");
                    ingredient.setQuantity(weight);

                    if (element.getFood().getNutrients().getEnercKcal() != null) {
                        ingredient.setCalories(calculateCalories(element.getFood().getNutrients().getEnercKcal(), weight));
                    } else {
                        ingredient.setCalories(0);
                    }

                    if (element.getFood().getNutrients().getProcnt() != null) {
                        ingredient.setProtein(calculateNutrients(element.getFood().getNutrients().getProcnt(), weight));
                    } else {
                        ingredient.setProtein(0d);
                    }

                    if (element.getFood().getNutrients().getFat() != null) {
                        ingredient.setFat(calculateNutrients(element.getFood().getNutrients().getFat(), weight));
                    } else {
                        ingredient.setFat(0d);
                    }

                    if (element.getFood().getNutrients().getChocdf() != null) {
                        ingredient.setCarbs(calculateNutrients(element.getFood().getNutrients().getChocdf(), weight));
                    } else {
                        ingredient.setCarbs(0d);
                    }

                    if (element.getFood().getNutrients().getFibtg() != null) {
                        ingredient.setFiber(calculateNutrients(element.getFood().getNutrients().getFibtg(), weight));
                    } else {
                        ingredient.setFiber(0d);
                    }

                    return ingredient;
                }).collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    private int calculateCalories(Double calories, int weight) {
        return (int) (((double) weight / 100) * calories);
    }

    private double calculateNutrients(Double nutrients, int weight) {
        double result = ((double) weight / 100) * nutrients;
        return dataParser.roundDouble(result, 2);
    }

}
