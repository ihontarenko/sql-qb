package pro.javadev.sql.internal.ast;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.node.AbstractNode;
import pro.javadev.sql.internal.render.Renderer;
import pro.javadev.sql.internal.render.RendererContext;

import java.util.List;

import static pro.javadev.sql.internal.common.StringUtils.underscored;

public abstract class ASTNode extends AbstractNode {

    public <T extends ASTNode> T getChild(Class<T> clazz) {
        return findFirst(clazz);
    }

    public <T extends ASTNode> List<T> getChildren(Class<T> clazz) {
        return findAll(clazz);
    }

    public String interpret(SQLDialect dialect, RendererContext context) {
        // default interpret method with interacting with renderers
        Renderer<ASTNode> renderer = context.getRenderer(dialect, (Class<ASTNode>) this.getClass());

        return renderer.render(dialect, context, this);
    }

    public String name() {
        return underscored(getClass().getSimpleName(), true);
    }

    @Override
    public String toString() {
        return "%s".formatted(name());
    }

}
