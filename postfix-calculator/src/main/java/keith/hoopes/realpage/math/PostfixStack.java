package keith.hoopes.realpage.math;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import keith.hoopes.realpage.math.tokens.BigDecimalToken;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A simple class for storing a value.
 * In the future, this would be persisted
 * to a data store.
 *
 * The Jackson annotations are used so that
 * this object can be easily and properly
 * converted to and from JSON when used in
 * a RESTful API.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostfixStack{

    /**
     * I don't think this is needed at the moment, but I included it
     * since I intend it to be used in restful calls to this base application
     * to retrieve the stored value to be operated on.
     */
    private UUID id = UUID.randomUUID();

    /**
     * Contains the stack/queue of token values to be operated on.
     */
    private ConcurrentLinkedDeque<BigDecimalToken> deque = new ConcurrentLinkedDeque<>();

    public boolean isEmpty(){

        return deque.isEmpty();
    }

    public Optional<BigDecimalToken> peek(){

        return Optional.ofNullable(deque.peek());
    }

    public void push(@Nonnull final BigDecimalToken token){

        deque.push(token);
    }

    public Optional<BigDecimalToken> pop(){

        return Optional.ofNullable(deque.isEmpty() ? null : deque.pop());
    }

    @Nonnull
    @JsonGetter("id")
    public UUID getId(){

        return id;
    }

    @JsonSetter("id")
    public void setId(@Nonnull final UUID id){

        this.id = id;
    }

    @JsonSetter("deque")
    public List<BigDecimalToken> getDeque(){

        return new ArrayList<>(deque);
    }

    @JsonGetter("deque")
    public void setDeque(@Nonnull final List<BigDecimalToken> deque){

        this.deque = new ConcurrentLinkedDeque<>(deque);
    }
}
