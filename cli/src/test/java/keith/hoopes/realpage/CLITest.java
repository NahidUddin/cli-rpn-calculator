package keith.hoopes.realpage;

import keith.hoopes.realpage.cli.CLI;
import keith.hoopes.realpage.math.PostfixCalculator;
import keith.hoopes.realpage.math.data.GreedyInMemoryPostfixStackRepository;
import org.junit.Test;

import java.text.DecimalFormat;

import static junit.framework.TestCase.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class CLITest{

    private static CLI cli(){

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(0);
        format.setGroupingUsed(false);

        return new CLI(
            new GreedyInMemoryPostfixStackRepository(),
            new PostfixCalculator(1));
    }

    @Test
    public void emptyCommand(){

        final CLI cli = cli();

        assertEquals("0", cli.execute(null));
        assertEquals("0", cli.execute("          "));
        assertEquals("42", cli.execute("        42  "));
        assertEquals("42", cli.execute(null));
        assertEquals("42", cli.execute(""));
        assertEquals("42", cli.execute(" "));
    }

    @Test
    public void addition(){

        final CLI cli = cli();

        assertEquals("2", cli.execute("1 1 +"));
        assertEquals("2", cli.execute("1 1.0 +"));
        assertEquals("3", cli.execute("1 +"));
        assertEquals("2.1", cli.execute("1 1.1 +"));
        assertEquals("2", cli.execute("1 1.0 +"));
        assertEquals("3.1", cli.execute("1.1 +"));
    }

    @Test
    public void subtract(){

        final CLI cli = cli();

        assertEquals("0", cli.execute(null));
        assertEquals("0", cli.execute("3.1 3.1 -"));
        assertEquals("-3.1", cli.execute("3.1 -"));
        assertEquals("-0.1", cli.execute("-3 -"));

    }

    @Test
    public void multiply(){

        final CLI cli = cli();

        assertEquals("0", cli.execute(null));//reset
        assertEquals("1.1", cli.execute("1 * 1.1"));
        assertEquals("1", cli.execute("1 * 1.0"));
        assertEquals("1.1", cli.execute("* 1.1"));
    }

    @Test
    public void division(){

        final CLI cli = cli();

        assertEquals("0", cli.execute(null));
        assertEquals("0", cli.execute("3 / "));
        assertEquals("1", cli.execute("3.1 3.1 /"));
        assertEquals("0.3", cli.execute("3 /"));//test infinite decimal results
        assertEquals("-1", cli.execute("3.1 -3.1 /"));
        assertEquals("1", cli.execute("-1 /"));
        assertEquals("1", cli.execute("1"));
        assertEquals("Error: Divide by Zero", cli.execute("0 /"));
    }

    @Test
    public void largeExpressionTest(){

        assertEquals("42", cli().execute("6 5 2 - 4 + *"));
    }
    @Test
    public void testPrecision(){

        final CLI cli = cli();

        assertEquals("42.2", cli.execute("42.2"));
    }
}