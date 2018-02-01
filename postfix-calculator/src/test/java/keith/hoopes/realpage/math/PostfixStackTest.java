package keith.hoopes.realpage.math;

import keith.hoopes.realpage.math.tokens.BigDecimalToken;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@SuppressWarnings("ConstantConditions")
class PostfixStackTest{

    @Test
    void isEmpty(){

        PostfixStack stack = new PostfixStack();
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushThenPeekTest(){

        PostfixStack stack = new PostfixStack();
        stack.push(BigDecimalToken.create(BigDecimal.TEN));
        assertEquals(BigDecimal.TEN,
            stack
                .peek()
                .get()
                .getValue());
    }

    @Test
    void pushThenPopTest(){

        PostfixStack stack = new PostfixStack();
        stack.push(BigDecimalToken.create(BigDecimal.TEN));
        assertEquals(BigDecimal.TEN,
            stack
                .pop()
                .get()
                .getValue());
    }

    @Test
    void idValueTest(){

        PostfixStack stack = new PostfixStack();
        assertNotNull(stack.getId());
        UUID change = UUID.randomUUID();
        stack.setId(change);
        assertEquals(change, stack.getId());
    }

    @Test
    void dequeTest(){

        PostfixStack stack = new PostfixStack();
        stack.push(BigDecimalToken.create(BigDecimal.TEN));
        stack.push(BigDecimalToken.create(BigDecimal.ONE));

        List<BigDecimalToken> deque;

        deque = stack.getDeque();
        assertNotNull(deque);
        assertTrue(deque.size() == 2);
        assertEquals(BigDecimal.TEN, deque.get(0).getValue());
        assertEquals(BigDecimal.ONE, deque.get(1).getValue());
        assertEquals(BigDecimal.ONE, stack.pop().get().getValue());
        assertEquals(BigDecimal.TEN, stack.pop().get().getValue());

        stack.setDeque(asList(
            BigDecimalToken.create(BigDecimal.TEN),
            BigDecimalToken.create(BigDecimal.ONE)
        ));
        deque = stack.getDeque();
        assertNotNull(deque);
        assertTrue(deque.size() == 2);
        assertEquals(BigDecimal.TEN, deque.get(0).getValue());
        assertEquals(BigDecimal.ONE, deque.get(1).getValue());
        assertEquals(BigDecimal.ONE, stack.pop().get().getValue());
        assertEquals(BigDecimal.TEN, stack.pop().get().getValue());
    }
}