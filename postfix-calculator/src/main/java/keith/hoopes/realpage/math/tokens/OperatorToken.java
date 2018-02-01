package keith.hoopes.realpage.math.tokens;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import keith.hoopes.realpage.math.PostFixOperator;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class OperatorToken extends Token<PostFixOperator>{

    private OperatorToken(@Nonnull final PostFixOperator value){

        super(value);
    }

    @Nonnull
    @JsonCreator
    public static OperatorToken create(@Nonnull @JsonProperty("value") final PostFixOperator value){

        return new OperatorToken(value);
    }

    @Nonnull
    @JsonValue
    public String toString(){

        Map<String, PostFixOperator> map = new HashMap<>();
        map.put("value", getValue());
        try{
            return new ObjectMapper().writeValueAsString(map);
        }catch(JsonProcessingException e){
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
