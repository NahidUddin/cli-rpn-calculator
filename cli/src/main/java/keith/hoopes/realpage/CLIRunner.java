package keith.hoopes.realpage;

import keith.hoopes.realpage.cli.CLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This was simplified in order to create a more cohesive and flexible design
 * in the future.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Component("cliRunner")
public class CLIRunner implements CommandLineRunner{

    private final CLI cli;

    @Autowired
    public CLIRunner(@Qualifier("cli") final CLI cli){

        this.cli = cli;
    }

    @Override
    public void run(final String... args){

        cli.run();
    }
}