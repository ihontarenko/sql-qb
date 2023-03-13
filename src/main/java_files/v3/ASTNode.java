package pro.javadev.sql.v3;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    protected List<ASTNode> children = new ArrayList<>();

    public void addChild(ASTNode child) {
        children.add(child);
    }

    public List<ASTNode> getChildrenList() {
        return children;
    }

    public <T extends ASTNode> T getChild(Class<T> clazz) {
        for (ASTNode child : children) {
            if (clazz.isInstance(child)) {
                return clazz.cast(child);
            }
        }

        return null;
    }

    public <T extends ASTNode> List<T> getChildrenList(Class<T> clazz) {
        List<T> matchingChildren = new ArrayList<>();

        for (ASTNode child : children) {
            if (clazz.isInstance(child)) {
                matchingChildren.add(clazz.cast(child));
            }
        }

        return matchingChildren;
    }

    public abstract String interpret(SQLDialect dialect);
}
