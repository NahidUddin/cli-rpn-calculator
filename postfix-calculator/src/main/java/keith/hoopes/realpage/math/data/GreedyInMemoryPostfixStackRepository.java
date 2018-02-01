package keith.hoopes.realpage.math.data;

import keith.hoopes.realpage.math.PostfixStack;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intended only as a placeholder to show intent of storing the PostfixStack
 * in a repository for session persistence. In the future, the implementation
 * methods will go away, and the interface will extend
 * "org.springframework.data.repository.CrudRepository" from the Spring Data
 * project. The project could then also be used to auto-generate an implementation
 * and to manage transactions.
 *
 * There are many methods that are implemented here, but which are not used (currently).
 * This was done simply as a means to demonstrate how one might implement a greedy
 * in-memory repository.
 *
 * The store is "greedy" because it does not and cannot share values across a clustered
 * service implementation.
 *
 * This implementation is backed by a thread-safe {@link ConcurrentHashMap}, but
 * is neither transactional nor managed by any type of transactional service and manager.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class GreedyInMemoryPostfixStackRepository implements PostfixStackRepository{

    private final Map<UUID, PostfixStack> store = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public PostfixStack save(@Nonnull final PostfixStack entity){

        store.put(entity.getId(), entity);
        return store.get(entity.getId());
    }

    @Override
    @Nonnull
    public <S extends PostfixStack> Iterable<S> saveAll(@Nonnull final Iterable<S> entities){

        entities.forEach(s -> store.put(s.getId(), s));

        //noinspection unchecked
        return (Iterable<S>) new ArrayList<>(store.values());
    }

    @Nonnull
    @Override
    public Optional<PostfixStack> findById(final UUID uuid){

        return Optional
            .ofNullable(
                store.get(uuid));
    }

    @Override
    public boolean existsById(@Nonnull final UUID uuid){

        return store.containsKey(uuid);
    }

    @Override
    @Nonnull
    public Iterable<PostfixStack> findAll(){

        return new HashSet<>(store.values());
    }

    @Override
    @Nonnull
    public Iterable<PostfixStack> findAllById(@Nonnull final Iterable<UUID> uuids){

        return StreamSupport
            .stream(uuids.spliterator(), false)
            .map(store::get)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    @Override
    public long count(){

        return store.size();
    }

    @Override
    public void deleteById(@Nonnull final UUID uuid){

        store.remove(uuid);
    }

    @Override
    public void delete(@Nonnull final PostfixStack entity){

        store.remove(entity.getId());
    }

    @Override
    public void deleteAll(@Nonnull final Iterable<? extends PostfixStack> entities){

        entities.forEach(p -> store.remove(p.getId()));
    }

    @Override
    public void deleteAll(){

        store.clear();
    }
}
