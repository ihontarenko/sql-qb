package pro.javadev.sql.common.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static pro.javadev.sql.common.node.Node.Order.ASC;
import static pro.javadev.sql.common.node.Node.Order.DESC;

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
        return Objects.nonNull(find(node));
    }

    default boolean exist(Class<?> type) {
        return !find(type).isEmpty();
    }

    default List<Node> find(Class<?> type) {
        List<Node>      result = new ArrayList<>();
        Predicate<Node> tester = type::isInstance;

        if (hasChildren()) {
            for (Node child : children()) {
                if (tester.test(child)) {
                    result.add(child);
                }
                result.addAll(child.find(type));
            }
        }

        return result;
    }

    default Node find(Node node) {
        return find(node, DESC);
    }

    default Node find(Node node, Order direction) {
        Node result = null;

        if (!this.equals(node)) {
            switch (direction) {
                case ASC:
                    if (!isRoot()) {
                        Node parent = parent();
                        if (parent.equals(node)) {
                            result = parent;
                        } else {
                            result = parent.find(node, ASC);
                        }
                    }
                    break;
                case DESC:
                    if (hasChildren()) {
                        for (Node child : children()) {
                            if ((result = child.equals(node) ? child : child.find(node)) != null) {
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

    default void execute(Consumer<Node> executor) {
        executor.accept(this);

        if (hasChildren()) {
            for (Node child : children()) {
                child.execute(executor);
            }
        }
    }

    enum Order {DESC, ASC}

}
