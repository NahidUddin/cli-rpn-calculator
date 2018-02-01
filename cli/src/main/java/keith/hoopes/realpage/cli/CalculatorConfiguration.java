package keith.hoopes.realpage.cli;

import keith.hoopes.realpage.math.PostfixCalculator;
import keith.hoopes.realpage.math.data.GreedyInMemoryPostfixStackRepository;
import keith.hoopes.realpage.math.data.PostfixStackRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Configuration
public class CalculatorConfiguration{

    @Bean
    public PostfixCalculator postfixCalculator(@Value("${math.MaximumFractionDigits:1}") final Integer maximumFractionDigits){

        return new PostfixCalculator(maximumFractionDigits);
    }

    @Bean
    public PostfixStackRepository postfixStackRepository(){

        return new GreedyInMemoryPostfixStackRepository();
    }
}
