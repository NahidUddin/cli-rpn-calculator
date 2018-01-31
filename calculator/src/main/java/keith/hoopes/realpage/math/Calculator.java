package keith.hoopes.realpage.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator{

    private final Integer maxDecimals;

    public Calculator(final Integer maxDecimals){

        if(maxDecimals == null){
            throw new IllegalArgumentException("'maxDecimals' is required.");
        }
        this.maxDecimals = maxDecimals;
    }

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
    public String execute(final String command){

        if(command == null || "".equals(command.trim())){
            return roundToString(currentValue.getValue());
        }
        return process(tokenize(command));
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
    private String process(final List<String> commands){

        assert commands != null : "Implementation error: commands array was null";

        Operand opToPop = null;

        for(String token : commands){
            final Operand nextOp;
            final BigDecimal nextVal = convertOrNull(token);

            if(nextVal == null){
                nextOp = Operand.forSymbol(token);
            }else{
                nextOp = null;
            }
            if(nextOp == null && nextVal == null){
                throw new CalculationException("Invalid Token in Operation: " + token);
            }
            if(nextVal != null){
                if(opToPop == null){
                    currentValue.setValue(nextVal);
                }else{
                    currentValue.setValue(
                        opToPop.execute(
                            currentValue.getValue(),
                            nextVal
                        ));
                }
            }else{
                opToPop = nextOp;
            }
        }
        return roundToString(currentValue.getValue());
    }

    /**
     * Creates a new BigDecimal rounded to the configured precision.
     *
     * @param targetValue The value to convert and round
     *
     * @return the rounded BigDecimal, or null if the targetValue was not a valid number.
     */
    private static BigDecimal convertOrNull(final String targetValue){

        BigDecimal answer;
        try{
            answer = new BigDecimal(targetValue);
        }catch(NumberFormatException ignored){
            answer = null;
        }
        return answer;
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
