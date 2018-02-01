package keith.hoopes.realpage.math.tokens;

import keith.hoopes.realpage.math.PostFixOperator;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public final class TokenFactory{

    public static Optional<Token> of(final String token){

        return Optional.ofNullable(
            convertToBigDecimal(token)
                .orElseGet(() ->
                    convertOperatorToken(token)
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

        BigDecimalToken answer;
        try{
            answer = BigDecimalToken.create(new BigDecimal(targetValue));
        }catch(NumberFormatException ignored){
            answer = null;
        }
        return Optional.ofNullable(answer);
    }

    private static Optional<Token> convertOperatorToken(final String targetValue){

        return Optional
            .ofNullable(
                PostFixOperator.forSymbol(targetValue)
            )
            .map(OperatorToken::create);
    }
}
