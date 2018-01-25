package keith.hoopes.realpage;

import keith.hoopes.realpage.math.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CLIRunner.class)
public class CLIRunnerTest{

    @Autowired
    CLI cli;

    @Test
    public void load(){

        assertNotNull("null", cli);
    }
}