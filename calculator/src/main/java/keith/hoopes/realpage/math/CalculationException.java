package keith.hoopes.realpage.math;

/**
 * Used to encapsulate any exception thrown as an exception that can be handled by the commandline.
 */
public class CalculationException extends RuntimeException{

    public CalculationException(final String message){

        super(message);
    }
}
