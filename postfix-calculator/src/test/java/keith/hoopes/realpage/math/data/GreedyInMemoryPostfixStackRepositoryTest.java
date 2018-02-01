package keith.hoopes.realpage.math.data;

import keith.hoopes.realpage.math.PostfixStack;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class GreedyInMemoryPostfixStackRepositoryTest{

    @Test
    void save(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();
        PostfixStack stack = new PostfixStack();
        assertEquals(stack.getId(), repo.save(stack).getId());
    }

    @Test
    void saveAll(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();
        List<PostfixStack> entities = asList(
            new PostfixStack(),
            new PostfixStack(),
            new PostfixStack()
        );
        repo.saveAll(entities);

        entities.forEach(
            e ->
                assertNotNull(
                    repo.findById(
                        e.getId()
                    )));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void findById(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();
        PostfixStack stack = new PostfixStack();
        repo.save(stack);

        assertEquals(stack, repo.findById(stack.getId()).get());
    }

    @Test
    void existsById(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();
        PostfixStack stack = new PostfixStack();
        repo.save(stack);

        assertTrue(repo.existsById(stack.getId()));
        assertFalse(repo.existsById(UUID.randomUUID()));
    }

    @Test
    void findAll(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();

        List<PostfixStack> entities = asList(
            new PostfixStack(),
            new PostfixStack(),
            new PostfixStack()
        );
        repo.saveAll(entities);

        repo
            .findAll()
            .forEach(p ->
                assertTrue(entities.contains(p)));
    }

    @Test
    void findAllById(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();

        List<PostfixStack> entities = asList(
            new PostfixStack(),
            new PostfixStack(),
            new PostfixStack()
        );
        repo.saveAll(entities);

        repo.findAllById(
            entities
                .stream()
                .map(PostfixStack::getId)
                .collect(Collectors.toList()))
            .forEach(
                p ->
                    assertTrue(entities.contains(p))
            );

    }

    @Test
    void count(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();

        List<PostfixStack> entities = asList(
            new PostfixStack(),
            new PostfixStack(),
            new PostfixStack()
        );
        repo.saveAll(entities);

        assertEquals(3L, repo.count());
    }

    @Test
    void deleteById(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();
        PostfixStack example = new PostfixStack();

        repo.save(example);
        assertEquals(
            example.getId(),
            repo
                .findById(example.getId())
                .orElse(new PostfixStack())
                .getId());

        repo.deleteById(example.getId());
        assertNull(repo
            .findById(example.getId())
            .orElse(null));
    }

    @Test
    void delete(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();

        PostfixStack sample = new PostfixStack();
        repo.save(sample);
        assertNotNull(
            repo
                .findById(sample.getId())
                .orElse(null));

        repo.delete(sample);
        assertNull(
            repo
                .findById(sample.getId())
                .orElse(null));
    }

    @Test
    void deleteAll(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();

        assertEquals(0L, repo.count());

        List<PostfixStack> entities = asList(
            new PostfixStack(),
            new PostfixStack(),
            new PostfixStack()
        );
        repo.saveAll(entities);

        assertEquals(3L, repo.count());
        repo.deleteAll();

        assertEquals(0L, repo.count());
    }

    @Test
    void deleteAllIterable(){

        final GreedyInMemoryPostfixStackRepository repo = new GreedyInMemoryPostfixStackRepository();
        assertEquals(0L, repo.count());

        List<PostfixStack> entities = asList(
            new PostfixStack(),
            new PostfixStack(),
            new PostfixStack()
        );
        repo.saveAll(entities);
        assertEquals(3L, repo.count());

        repo.save(new PostfixStack());
        assertEquals(4L, repo.count());

        repo.deleteAll(entities);
        assertEquals(1L, repo.count());
    }
}