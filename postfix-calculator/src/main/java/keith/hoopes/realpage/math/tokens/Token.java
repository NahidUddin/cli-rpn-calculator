package keith.hoopes.realpage.math.tokens;

import javax.annotation.Nonnull;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public abstract class Token<T>{

    private final T value;

    Token(@Nonnull final T value){

        this.value = value;
    }

    @Nonnull
    public T getValue(){

        return value;
    }
}
