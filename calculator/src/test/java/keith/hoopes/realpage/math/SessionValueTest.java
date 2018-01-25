package keith.hoopes.realpage.math;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class SessionValueTest{

    @Test
    public void gettersAndSetters(){

        SessionValue value = new SessionValue();
        assertNotNull("'id' should have default value",
          value.getId());
        assertNotNull("'value' should have a default value",
          value.getValue());
        assertEquals("'value' should be default to ZERO",
          BigDecimal.ZERO, value.getValue());
        
        value.setValue(BigDecimal.TEN);
        assertEquals("'value' should be modifiable",
          BigDecimal.TEN, value.getValue());
    }

}