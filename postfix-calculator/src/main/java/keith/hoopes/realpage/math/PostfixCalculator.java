package keith.hoopes.realpage.math;

import keith.hoopes.realpage.math.tokens.BigDecimalToken;
import keith.hoopes.realpage.math.tokens.OperatorToken;
import keith.hoopes.realpage.math.tokens.Token;
import keith.hoopes.realpage.math.tokens.TokenFactory;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PostfixCalculator{

    private final Integer maxDecimals;

    public PostfixCalculator(final Integer maxDecimals){

        if(maxDecimals == null){
            throw new IllegalArgumentException("'maxDecimals' is required.");
        }
        this.maxDecimals = maxDecimals;
    }

    /**
     * The DecimalFormat class is not thread safe.
     * This acts as a helper/factory method to ensure
     * the same settings are used in each instance.
     *
     * @return DecimalFormat
     */
    private DecimalFormat decimalFormat(){

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(maxDecimals);
        format.setMinimumFractionDigits(0);
        format.setGroupingUsed(false);
        return format;
    }

    /**
     * Executes the given command and returns the results.
     * Blank commands will result in return the currently set value.
     *
     * @param command The mathematical operation to execute.
     *
     * @return The results of the {@code command} being executed.
     *
     * @throws CalculationException if the command is not valid. This
     *     will not effect the current session value.
     */
    public String execute(
        final String command,
        @Nonnull final PostfixStack postfixStack){

        String answer;
        if(command == null || "".equals(command.trim())){
            answer = roundToString(
                postfixStack
                    .peek()
                    .orElse(
                        BigDecimalToken
                            .create(BigDecimal.ZERO))
                    .getValue()
            );
        }else{
            answer = process(
                tokenize(command),
                postfixStack);
        }
        return answer;
    }

    /**
     * Currently uses the ' ' character as the delimiter for all commands.
     * This method will need to be modified in the future to account for operations
     * not surrounded by a space, parenthesis, or general order of operations.
     *
     * This could probably benefit from being put into it's own "Tokenizer" class
     * for handling order of operations.
     *
     * @param command The string to turn into a list of tokens
     *
     * @return A list of String tokens to be processed.
     */
    private static List<String> tokenize(final String command){

        assert command != null : "'command' must not be null.";

        return Arrays.stream(command.split(" "))
                     .map(String::trim)
                     .filter(c -> c.length() > 0)
                     .collect(Collectors.toList());
    }

    /**
     * Calculates the results of the command against the current session value.
     *
     * @param commands The String of commands to execute.
     *
     * @return The string result of the command operations.
     */
    private String process(
        final List<String> commands,
        final PostfixStack postfixStack){

        assert commands != null : "Implementation error: commands list was null";
        assert postfixStack != null : "Implementation error: postfixStack was null";

        for(String command : commands){

            Token token = TokenFactory
                .of(command)
                .orElseThrow(() ->
                    new CalculationException("Invalid Token: " + command)
                );

            if(token instanceof BigDecimalToken){
                postfixStack
                    .push(
                        (BigDecimalToken) token);
            }else if(token instanceof OperatorToken){
                postfixStack.push(
                    BigDecimalToken.create(
                        ((OperatorToken) token)
                            .getValue()
                            .execute(
                                postfixStack
                                    .pop()
                                    .orElse(
                                        BigDecimalToken
                                            .create(BigDecimal.ZERO))
                                    .getValue(),
                                postfixStack
                                    .pop()
                                    .orElse(
                                        BigDecimalToken
                                            .create(BigDecimal.ZERO))
                                    .getValue()
                            )));
            }
        }

        return roundToString(
            postfixStack
                .peek()
                .orElse(
                    BigDecimalToken
                        .create(BigDecimal.ZERO))
                .getValue());
    }

    /**
     * Rounds the input and converts it to a String.
     *
     * @param value the {@link BigDecimal} to round and convert
     *
     * @return The String version of the {@code value}
     */
    private String roundToString(final BigDecimal value){

        assert value != null : "Cannot convert null BigDecimal values.";

        return decimalFormat().format(value);
    }
}
