package keith.hoopes.realpage.math;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class Token<T>{

    private final T value;

    private Token(final T value){

        if(value instanceof BigDecimal || value instanceof Operand){
            this.value = value;
        }
        throw new CalculationException("Invalid value in Token constructor: " + value);
    }

    public static Optional<Token> build(final String token){

        return Optional.ofNullable(
            convertToBigDecimal(token)
                .orElseGet(() ->
                    Optional
                        .of(Operand.forSymbol(token))
                        .map(Token::new)
                        .orElse(null)
                ));
    }

    /**
     * Creates a new BigDecimal rounded to the configured precision.
     *
     * @param targetValue The value to convert and round
     *
     * @return the rounded BigDecimal, or null if the targetValue was not a valid number.
     */
    private static Optional<Token> convertToBigDecimal(final String targetValue){

        Token<BigDecimal> answer;
        try{
            answer = new Token<>(new BigDecimal(targetValue));
        }catch(NumberFormatException ignored){
            answer = null;
        }
        return Optional.of(answer);
    }
}
