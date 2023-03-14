package pro.javadev.sql.ast;

import pro.javadev.sql.SQLDialect;
import pro.javadev.sql.common.node.AbstractNode;
import pro.javadev.sql.common.node.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode extends AbstractNode {

    public <T extends ASTNode> T getChild(Class<T> clazz) {
        Node result = null;

        for (Node child : children()) {
            if (clazz.isInstance(child)) {
                return clazz.cast(child);
            }
        }

        return (T) result;
    }

    public <T extends ASTNode> List<T> getChildren(Class<T> clazz) {
        List<T> matchingChildren = new ArrayList<>();

        for (Node child : children()) {
            if (clazz.isInstance(child)) {
                matchingChildren.add(clazz.cast(child));
            }
        }

        return matchingChildren;
    }

    public abstract String interpret(SQLDialect dialect);

}
