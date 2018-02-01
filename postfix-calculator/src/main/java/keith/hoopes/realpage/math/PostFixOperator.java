package keith.hoopes.realpage.math;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Copyright ${year}
 *
 * Represents an operation that can be executed on two operands (values).
 *
 * @author J. Keith Hoopes
 */
public enum PostFixOperator{

    ADD("+", (b, a) -> a.add(b)),
    SUBTRACT("-", (b, a) -> a.subtract(b)),
    MULTIPLY("*", (b, a) -> a.multiply(b)),
    DIVIDE("/", (b, a) -> {
        if(Objects.equals(b, BigDecimal.ZERO)){
            throw new CalculationException("Error: Divide by Zero");
        }
        return a.divide(b, RoundingMode.HALF_UP);
    });

    private final String symbol;

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;

    PostFixOperator(final String symbol, final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation){

        assert symbol != null;

        this.symbol = symbol;
        this.operation = operation;
    }

    /**
     * Helpful method for converting text symbols, into the
     * appropriate enum with it's accompanied operation.
     *
     * @param symbol The {@link String} value to convert to an enum
     *
     * @return The matching PostFixOperator.
     *
     * @throws CalculationException when an invalid symbol is found.
     */
    public static PostFixOperator forSymbol(final String symbol){

        return Arrays
            .stream(PostFixOperator.values())
            .filter(o -> o.getSymbol().equals(symbol))
            .findAny()
            .orElseThrow(() ->
                new CalculationException("Invalid symbol found: " + symbol)
            );
    }

    public String getSymbol(){

        return symbol;
    }

    /**
     * Since these are postfix operators, the "first" operand
     * is passed in as parameter two while the "second" operand
     * is passed in as parameter one.
     *
     * For example, the command "1 2 +" would be translated into the
     * following method call:
     *
     * {@code PostFixOperator.ADD.execute(2,1);}
     *
     * This is done so that chained "pop" calls on the {@link PostfixStack}
     * would be possible to use inside of the Java streaming API.
     *
     * @param second {@link BigDecimal}: the second value in a postfix command
     * @param first {@link BigDecimal}: the first value in a postfix command
     *
     * @return The results of the execution.
     */
    @Nonnull
    public BigDecimal execute(
        @Nonnull final BigDecimal second,
        @Nonnull final BigDecimal first){

        return operation.apply(second, first);
    }
}
