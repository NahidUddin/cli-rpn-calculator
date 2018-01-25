package keith.hoopes.realpage;

import keith.hoopes.realpage.math.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@SpringBootApplication
public class CLIRunner implements CommandLineRunner{

  @Autowired
  private CLI cli;

  public static void main(final String[] args){

    SpringApplication app = new SpringApplication(CLIRunner.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Bean("cli")
  public CLI cli(
    @Value("${math.MaximumFractionDigits:1}") final Integer maximumFractionDigits,
    @Value("${test-mode:false}") final boolean testMode){

    return new CLI(new Calculator(maximumFractionDigits), testMode);
  }

  @Override
  public void run(final String... args){

    cli.run();
  }
}