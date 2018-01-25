package keith.hoopes.realpage.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public enum Operand{

    ADD("+", BigDecimal::add),
    SUBTRACT("-", BigDecimal::subtract),
    MULTIPLY("*", BigDecimal::multiply),
    DIVIDE("/", (a, b) -> a.divide(b, RoundingMode.HALF_UP));

    private final String symbol;

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;

    Operand(final String symbol, final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation){

        assert symbol != null;

        this.symbol = symbol;
        this.operation = operation;
    }

    public static Operand forSymbol(final String symbol){

        return Arrays
          .stream(Operand.values())
          .filter(o -> o.getSymbol().equals(symbol))
          .findAny()
          .orElse(null);
    }

    public String getSymbol(){

        return symbol;
    }

    public BigDecimal execute(final BigDecimal first, final BigDecimal second){

        return operation.apply(first, second);
    }

}
