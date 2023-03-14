package pro.javadev.sql.common.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static pro.javadev.sql.common.node.Node.Order.UP;
import static pro.javadev.sql.common.node.Node.Order.DOWN;

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
        return getClass().isAssignableFrom(node.getClass());
    }

    default Node findFirst(Node node) {
        return findFirst(node, DOWN, Integer.MAX_VALUE);
    }

    default Node findFirst(Node node, Order order) {
        return findFirst(node, order, Integer.MAX_VALUE);
    }

    default Node findFirst(Node node, Order order, int depth) {
        return findFirst(n -> n.equals(node), order, depth);
    }

    default Node findFirst(Class<? extends Node> klass) {
        return findFirst(node -> node.getClass().isAssignableFrom(klass), DOWN, Integer.MAX_VALUE);
    }

    default Node findFirst(Class<? extends Node> klass, Order order) {
        return findFirst(node -> node.getClass().isAssignableFrom(klass), order, Integer.MAX_VALUE);
    }

    default Node findFirst(Class<? extends Node> klass, Order order, int depth) {
        return findFirst(n -> n.getClass().isAssignableFrom(klass), order, depth);
    }

    default Node findFirst(Predicate<Node> predicate, Order order, int depth) {
        Node result = null;

        if (!predicate.test(this)) {
            switch (order) {
                case UP:
                    if (depth > 0 && !isRoot()) {
                        Node parent = parent();
                        if (predicate.test(parent)) {
                            result = parent;
                        } else {
                            result = parent.findFirst(predicate, UP, depth - 1);
                        }
                    }
                    break;
                case DOWN:
                    if (depth > 0 && hasChildren()) {
                        for (Node child : children()) {
                            if ((result = predicate.test(child) ? child : child.findFirst(predicate, order, depth - 1)) != null) {
                                break;
                            }
                        }
                    }
                    break;
            }
        } else {
            result = this;
        }

        return result;
    }

    default List<Node> findAll(Node node) {
        return findAll(n -> n.equals(node), DOWN, Integer.MAX_VALUE);
    }

    default List<Node> findAll(Node node, Order order) {
        return findAll(n -> n.equals(node), order, Integer.MAX_VALUE);
    }

    default List<Node> findAll(Node node, int depth) {
        return findAll(n -> n.equals(node), DOWN, depth);
    }

    default List<Node> findAll(Node node, Order order, int depth) {
        return findAll(n -> n.equals(node), order, depth);
    }

    default List<Node> findAll(Class<? extends Node> klass) {
        return findAll(n -> n.getClass().isAssignableFrom(klass), DOWN, Integer.MAX_VALUE);
    }

    default List<Node> findAll(Class<? extends Node> klass, Order order) {
        return findAll(n -> n.getClass().isAssignableFrom(klass), order, Integer.MAX_VALUE);
    }

    default List<Node> findAll(Class<? extends Node> klass, int depth) {
        return findAll(n -> n.getClass().isAssignableFrom(klass), DOWN, depth);
    }

    default List<Node> findAll(Class<? extends Node> klass, Order order, int depth) {
        return findAll(n -> n.getClass().isAssignableFrom(klass), order, depth);
    }

    default List<Node> findAll(Predicate<Node> predicate, Order order, int depth) {
        List<Node> result = new ArrayList<>();

        switch (order) {
            case UP:
                if (depth > 0 && !isRoot()) {
                    Node parent = parent();
                    if (predicate.test(parent)) {
                        result.add(parent);
                    } else {
                        result.addAll(
                                parent.findAll(predicate, UP, depth - 1)
                        );
                    }
                }
                break;
            case DOWN:
                if (depth > 0 && hasChildren()) {
                    for (Node child : children()) {
                        if (predicate.test(child)) {
                            result.add(child);
                        }

                        result.addAll(
                                child.findAll(predicate, order, depth - 1)
                        );
                    }
                }
                break;
        }

        return result;
    }

    default void execute(Consumer<Node> executor) {
        executor.accept(this);

        if (hasChildren()) {
            for (Node child : children()) {
                child.execute(executor);
            }
        }
    }

    enum Order {DOWN, UP}

}
