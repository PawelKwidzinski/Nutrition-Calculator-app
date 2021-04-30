package pl.kwidzinski.caloriecalculator.util;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DataParser {

    public double roundDouble(double value, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
