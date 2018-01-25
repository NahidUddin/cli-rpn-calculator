package keith.hoopes.realpage.math;

import java.math.BigDecimal;
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
public class SessionValue{

    /**
     * I don't think this is needed at the moment, but I included it
     * since I intend it to be used in restful calls to this base application
     * to retrieve the stored value to be operated on.
     */
    private UUID id = UUID.randomUUID();

    /**
     * The value. Defaults to ZERO.
     */
    private BigDecimal value = BigDecimal.ZERO;

    public UUID getId(){

        return id;
    }

    public void setId(final UUID id){

        this.id = id;
    }

    public BigDecimal getValue(){

        return value;
    }

    public void setValue(final BigDecimal value){

        if(value == null){
            throw new CalculationException("Invalid Value: null");
        }
        this.value = value;
    }

}
