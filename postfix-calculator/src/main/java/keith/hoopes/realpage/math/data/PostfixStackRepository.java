package keith.hoopes.realpage.math.data;

import keith.hoopes.realpage.math.PostfixStack;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

/**
 * Intended only as a placeholder to show intent of storing the PostfixStack
 * in a repository for session persistence. In the future, the implementation
 * methods will go away, and the interface will extend "org.springframework.data.repository.CrudRepository".
 * The Spring Data project could then be used to auto-generate an implementation
 * and to manage transactions.
 *
 * There are many methods that are defined here, but which are not used (currently).
 * These were all taken from "org.springframework.data.repository.CrudRepository",
 * and modified to fit my needs; I did not (currently) wish to include a dependency on
 * Spring Data if I was not actually going to use it, and was easy to copy-paste and
 * modify the interface definitions.
 *
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public interface PostfixStackRepository{

    @Nonnull
    PostfixStack save(@Nonnull PostfixStack entity);

    @Nonnull
    <S extends PostfixStack> Iterable<S> saveAll(@Nonnull Iterable<S> entities);

    @Nonnull
    Optional<PostfixStack> findById(UUID uuid);

    boolean existsById(@Nonnull UUID uuid);

    @Nonnull
    Iterable<PostfixStack> findAll();

    @Nonnull
    Iterable<PostfixStack> findAllById(@Nonnull Iterable<UUID> uuids);

    long count();

    void deleteById(@Nonnull UUID uuid);

    void delete(@Nonnull PostfixStack entity);

    void deleteAll(@Nonnull Iterable<? extends PostfixStack> entities);

    void deleteAll();
}
