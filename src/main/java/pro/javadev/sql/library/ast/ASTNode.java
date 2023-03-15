package pro.javadev.sql.library.ast;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.node.AbstractNode;
import pro.javadev.sql.library.render.RendererContext;

import java.util.List;

public abstract class ASTNode extends AbstractNode {

    public <T extends ASTNode> T getChild(Class<T> clazz) {
        return (T) findFirst(clazz);
    }

    public <T extends ASTNode> List<T> getChildren(Class<T> clazz) {
        return findAll(clazz);
    }

    public String interpret(SQLDialect dialect, RendererContext context) {
        return context.getRenderer(dialect, this).render(dialect, context, this);
    }

}