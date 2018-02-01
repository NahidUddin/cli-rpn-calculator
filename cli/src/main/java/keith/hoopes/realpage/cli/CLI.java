package keith.hoopes.realpage.cli;

import keith.hoopes.realpage.math.CalculationException;
import keith.hoopes.realpage.math.PostfixCalculator;
import keith.hoopes.realpage.math.PostfixStack;
import keith.hoopes.realpage.math.data.PostfixStackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

import static org.springframework.util.StringUtils.*;

/**
 * Contains all functionality required for user interaction
 * through the terminal.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Component("cli")
public class CLI{

    private final UUID sessionId;

    /**
     * The use of a repository for the CLI is obvious overkill.
     * This is more a demonstration of the concept of service
     * and session specific values which could be expand on in
     * future iterations when creating a RESTful API.
     */
    private final PostfixStackRepository postfixStackRepository;

    private final PostfixCalculator postfixCalculator;

    @Autowired
    public CLI(
        final PostfixStackRepository postfixStackRepository,
        final PostfixCalculator postfixCalculator){

        this.postfixStackRepository = postfixStackRepository;
        this.postfixCalculator = postfixCalculator;
        this.sessionId = postfixStackRepository.save(new PostfixStack()).getId();
    }

    public void run(){

        nextCommand();
        try(Scanner scanner = new Scanner(System.in)){

            String command = trimWhitespace(scanner.nextLine());
            while(doContinue(command)){

                print(execute(command));
                nextCommand();
                command = trimWhitespace(scanner.nextLine());
            }
        }
    }

    /**
     * Prints out a friendly "> " sequence to help the user
     * understand that the CLI is waiting for further input.
     */
    private static void nextCommand(){

        System.out.print("\n> ");
    }

    /**
     * Parses and executes the given command sequence.
     *
     * @param command The series of tokens entered by the user.
     *
     * @return The results of the command in as a {@link String} that
     *     can be printed out for the user. This may contain simple error
     *     messages or helpful documentation.
     */
    public String execute(final String command){

        String answer;
        try{
            if("help".equals(command) || "h".equals(command)){

                answer = help();

            }else{
                answer = postfixCalculator
                    .execute(command,
                        postfixStackRepository
                            .findById(sessionId)
                            .orElseThrow(() ->
                                new IllegalStateException("Could not find session PostfixStack.")
                            ));
            }
        }catch(CalculationException e){
            answer = e.getMessage();
        }
        return answer;
    }

    private static String help(){

        return "" + //I Hate how my auto-formatter indents the second line. It's ocd of me, and I can't help it :P
            "**************************\n" +
            "* Postfix Calculator CLI *\n" +
            "**************************\n" +
            "\n" +
            "A postfix expression is an expression in which the operands are followed by the operator " +
            "unlike expressions in the familiar format, where the operator is in between the operands. " +
            "For example, the standard \"familiar\" expression \"6 * (5 - 2 + 4)\" can be written in " +
            "postfix form as \"6 5 2 - 4 + *\"\n\n" +
            "Not every mathematical operator is implemented in this version at the moment.\n\n" +
            "Possible operators:\n\n" +
            "\tADDITION: + \n" +
            "\tSUBTRACTION: - \n" +
            "\tMULTIPLICATION: * \n" +
            "\tDIVISION: / \n\n" +
            "Examples: \n\n" +
            "\t*Basic Syntax\n" +
            "\t> 1 1 +\n" +
            "\t2\n" +
            "\t*Decimals Allowed\n" +
            "\t> 1.1 1 -\n" +
            "\t0.1\n" +
            "\t*Results Rounded\n" +
            "\t> 1.01 1.0 + \n" +
            "\t2\n" +
            "\t*Trailing '0' Decimal removed\n" +
            "\t> 1.0 1.0 +\n" +
            "\t2\n" +
            "\t*Previous Values Persist\n" +
            "\t> 1 - \n" +
            "\t1\n" +
            "\t*Spaces are required\n" +
            "\t> -1\n" +
            "\t-1\n" +
            "\t*Operator only is allowed, and will calculate the last two numbers entered\n" +
            "\t> 1 1\n" +
            "\t1\n" +
            "\t> +\n" +
            "\t2\n" +
            "\t*Simple number entry will show the last number entered\n" +
            "\t> 1 2 3\n" +
            "\t3\n";
    }

    private static void print(final String value){

        assert value != null;

        System.out.print(value);
    }

    private static boolean doContinue(final String command){

        return !"q".equalsIgnoreCase(command) && !"^D".equalsIgnoreCase(command);
    }

}