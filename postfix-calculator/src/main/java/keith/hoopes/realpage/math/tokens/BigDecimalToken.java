package keith.hoopes.realpage.math.tokens;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class BigDecimalToken extends Token<BigDecimal>{

    private BigDecimalToken(@Nonnull final BigDecimal value){

        super(value);
    }

    @Nonnull
    @JsonCreator
    public static BigDecimalToken create(@Nonnull @JsonProperty("value") final BigDecimal value){

        return new BigDecimalToken(value);
    }

    @Nonnull
    @JsonValue
    public String toString(){

        Map<String, BigDecimal> map = new HashMap<>();
        map.put("value", getValue());
        try{
            return new ObjectMapper().writeValueAsString(map);
        }catch(JsonProcessingException e){
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
