package pro.javadev.sql.internal.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract public class AbstractNode implements Node {

    protected final List<Node> children = new ArrayList<>();
    protected       Node       parent;

    public AbstractNode() {
        this(null);
    }

    public AbstractNode(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public boolean hasParent() {
        return this.parent != null;
    }

    @Override
    public Node parent() {
        return this.parent;
    }

    @Override
    public void parent(Node node) {
        this.parent = node;
    }

    @Override
    public Node[] children() {
        Node[] nodes = new Node[this.children.size()];

        return this.children.toArray(nodes);
    }

    @Override
    public boolean delete(Node node) {
        return this.children.remove(node);
    }

    @Override
    public void add(Node node) {
        if (this != node) {

            if (Objects.nonNull(findFirst(node, Way.UP, Integer.MAX_VALUE))) {
                throw new NodeException("CIRCULAR REFERENCE: THE CURRENT NODE IS A DESCENDANT OF THE PASSED");
            }

            node.parent(this);
            this.children.add(node);
        }
    }

}
