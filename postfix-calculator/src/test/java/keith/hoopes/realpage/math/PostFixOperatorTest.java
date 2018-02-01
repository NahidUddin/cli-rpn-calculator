package keith.hoopes.realpage.math;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static java.math.BigDecimal.*;
import static keith.hoopes.realpage.math.PostFixOperator.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class PostFixOperatorTest{

    private static DecimalFormat decimalFormat(){

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(0);
        format.setGroupingUsed(false);
        return format;
    }

    @Test
    void forSymbolTest(){

        assertEquals(ADD, forSymbol("+"));
        assertEquals(SUBTRACT, forSymbol("-"));
        assertEquals(MULTIPLY, forSymbol("*"));
        assertEquals(DIVIDE, forSymbol("/"));
        assertThrows(CalculationException.class,
            () -> forSymbol("^")
        );
    }

    @Test
    void executeTest(){

        assertEquals("2",
            decimalFormat().format(
                ADD.execute(ONE, ONE)));

        assertEquals("0",
            decimalFormat().format(
                SUBTRACT.execute(ONE, ONE)));

        assertEquals("1",
            decimalFormat().format(
                MULTIPLY.execute(ONE, ONE)));

        assertEquals("1",
            decimalFormat().format(
                DIVIDE.execute(ONE, ONE)));

        assertEquals("0",
            decimalFormat().format(
                DIVIDE.execute(ONE, ZERO)));

        assertThrows(CalculationException.class,
            () -> DIVIDE.execute(ZERO, ONE));
    }
}