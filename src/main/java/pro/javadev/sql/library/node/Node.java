package pro.javadev.sql.library.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static pro.javadev.sql.library.node.Node.Way.DOWN;

@SuppressWarnings({"unused"})
public interface Node {

    boolean hasChildren();

    boolean hasParent();

    default boolean isRoot() {
        return !hasParent();
    }

    Node parent();

    void parent(Node node);

    Node[] children();

    void add(Node node);

    boolean delete(Node node);

    default boolean exist(Node node) {
        return Objects.nonNull(findFirst(node));
    }

    default boolean exist(Class<? extends Node> type) {
        return !findAll(type).isEmpty();
    }

    default boolean same(Node node) {
        return same(node.getClass());
    }

    default boolean same(Class<? extends Node> klass) {
        return getClass().isAssignableFrom(klass);
    }

    default <T extends Node> T findFirst(Node node) {
        return findFirst(node, DOWN, Integer.MAX_VALUE);
    }

    default <T extends Node> T findFirst(Node node, Way way) {
        return findFirst(node, way, Integer.MAX_VALUE);
    }

    default <T extends Node> T findFirst(Node node, Way way, int depth) {
        return findFirst(node::equals, way, depth);
    }

    default <T extends Node> T findFirst(Class<? extends Node> klass) {
        return findFirst(node -> node.getClass().isAssignableFrom(klass), DOWN, Integer.MAX_VALUE);
    }

    default <T extends Node> T findFirst(Class<? extends Node> klass, Way way) {
        return findFirst(node -> node.getClass().isAssignableFrom(klass), way, Integer.MAX_VALUE);
    }

    default <T extends Node> T findFirst(Class<? extends Node> klass, Way way, int depth) {
        return findFirst(n -> n.getClass().isAssignableFrom(klass), way, depth);
    }

    default <T extends Node> T findFirst(Predicate<Node> predicate, Way way, int depth) {
        T result = null;

        switch (way) {
            case UP:
                if (depth > 0 && !isRoot()) {
                    Node parent = parent();
                    if (predicate.test(parent)) {
                        result = (T) parent;
                    } else {
                        result = parent.findFirst(predicate, way, depth - 1);
                    }
                }
                break;
            case DOWN:
                if (depth > 0 && hasChildren()) {
                    for (Node child : children()) {
                        if (predicate.test(child)) {
                            result = (T) child;
                        } else {
                            result = child.findFirst(predicate, way, depth - 1);
                        }

                        if (result != null) {
                            break;
                        }
                    }
                }
                break;
        }


        return result;
    }

    default <T extends Node> List<T> findAll(Node node) {
        return findAll(node::equals, DOWN, Integer.MAX_VALUE);
    }

    default <T extends Node> List<T> findAll(Node node, Way way) {
        return findAll(node::equals, way, Integer.MAX_VALUE);
    }

    default <T extends Node> List<T> findAll(Node node, int depth) {
        return findAll(node::equals, DOWN, depth);
    }

    default <T extends Node> List<T> findAll(Node node, Way way, int depth) {
        return findAll(node::equals, way, depth);
    }

    default <T extends Node> List<T> findAll(Class<? extends Node> klass) {
        return findAll(n -> klass.isAssignableFrom(n.getClass()), DOWN, Integer.MAX_VALUE);
    }

    default <T extends Node> List<T> findAll(Class<? extends Node> klass, Way way) {
        return findAll(n -> klass.isAssignableFrom(n.getClass()), way, Integer.MAX_VALUE);
    }

    default <T extends Node> List<T> findAll(Class<? extends Node> klass, int depth) {
        return findAll(n -> klass.isAssignableFrom(n.getClass()), DOWN, depth);
    }

    default <T extends Node> List<T> findAll(Class<? extends Node> klass, Way way, int depth) {
        return findAll(n -> klass.isAssignableFrom(n.getClass()), way, depth);
    }

    default <T extends Node> List<T> findAll(Predicate<Node> predicate, Way way, int depth) {
        List<T> result = new ArrayList<>();

        switch (way) {
            case UP:
                if (depth > 0 && !isRoot()) {
                    T parent = (T) parent();
                    if (predicate.test(parent)) {
                        result.add(parent);
                    } else {
                        result.addAll(
                                parent.findAll(predicate, way, depth - 1)
                        );
                    }
                }
                break;
            case DOWN:
                if (depth > 0 && hasChildren()) {
                    for (Node child : children()) {
                        if (predicate.test(child)) {
                            result.add((T) child);
                        }

                        result.addAll(
                                child.findAll(predicate, way, depth - 1)
                        );
                    }
                }
                break;
        }

        return result;
    }

    default void execute(BiConsumer<Node, Integer> executor, int depth) {
        executor.accept(this, depth);
        if (hasChildren()) {
            for (Node child : children()) {
                child.execute(executor, depth + 1);
            }
        }
    }

    default void execute(BiConsumer<Node, Integer> executor) {
        execute(executor, 0);
    }

    enum Way {DOWN, UP}

}
