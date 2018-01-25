package keith.hoopes.realpage;

import keith.hoopes.realpage.math.CalculationException;
import keith.hoopes.realpage.math.Calculator;

import java.util.Scanner;

import static org.springframework.util.StringUtils.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class CLI{

  private final Calculator calculator;

  private final boolean testMode;

  public CLI(
    final Calculator calculator,
    final boolean testMode){

    this.calculator = calculator;
    this.testMode = testMode;
  }

  public void run(){

    if(testMode){
      return;
    }
    print("\n**************\n");
    print("\n* CALCULATOR *\n");
    print("\n**************\n");
    print("\nEnter 'q' to exit.\n");

    String command;
    nextCommand();
    try(Scanner scanner = new Scanner(System.in)){

      command = trimWhitespace(scanner.nextLine());
      while(doContinue(command)){
        print(execute(command));
        nextCommand();
        command = trimWhitespace(scanner.nextLine());
      }
    }
  }

  private static void nextCommand(){

    System.out.print("\n> ");
  }

  public String execute(final String command){

    try{
      if("help".equals(command) || "h".equals(command)){
        return help();
      }
      return calculator.execute(command);
    }catch(CalculationException e){
      return e.getMessage();
    }
  }

  private static String help(){

    return
      "Possible operators:\n\n" +
        "\tADDITION: + \n" +
        "\tSUBTRACTION: - \n" +
        "\tMULTIPLICATION: * \n" +
        "\tDIVISION: / \n\n" +
        "Examples: \n\n" +
        "\t*Basic Syntax\n" +
        "\t> 1 + 1\n\t2\n" +
        "\t*Decimals Allowed\n" +
        "\t> 1.1 - 1\n\t0.1\n" +
        "\t*Results Rounded\n" +
        "\t> 1.01 + 1.0\n\t2\n" +
        "\t*Trailing '0' Decimal removed\n" +
        "\t> 1.0 + 1.0\n\t2\n" +
        "\t*Previous Values Persist\n" +
        "\t> - 1\n\t1\n" +
        "\t*Spaces are required\n" +
        "\t> -1\n\t-1\n" +
        "\t*Simple number entry stores the first number\n" +
        "\t> 1 2\n\t1\n" +
        "\t*Trailing operators are ignored.\n" +
        "\t> 1 1 +\n\t1\n";
  }

  private static void print(final String value){

    assert value != null;

    System.out.print(value);
  }

  private static boolean doContinue(final String command){

    return !"q".equalsIgnoreCase(command) && !"^D".equalsIgnoreCase(command);
  }

}