package keith.hoopes.realpage.math;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Stack;
import java.util.UUID;

/**
 * A simple class for storing a value.
 * In the future, this would be persisted
 * in a data store.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class PostfixStack{

    /**
     * I don't think this is needed at the moment, but I included it
     * since I intend it to be used in restful calls to this base application
     * to retrieve the stored value to be operated on.
     */
    private UUID id = UUID.randomUUID();

    /**
     * The value. Defaults to ZERO.
     */
    private final Stack<BigDecimal> stack = new Stack<>();

    private BigDecimal value;

    public UUID getId(){

        return id;
    }

    public void push(final BigDecimal token){

        if(token == null){
            throw new CalculationException("Cannot push null values.");
        }
        stack.push(token);
    }

    public boolean empty(){

        return stack.empty();
    }

    public Optional<BigDecimal> pop(){

        return Optional.of(
            stack.pop()
        );
    }

}
