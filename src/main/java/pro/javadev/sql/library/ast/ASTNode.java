package pro.javadev.sql.library.ast;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.node.AbstractNode;
import pro.javadev.sql.library.render.Renderer;
import pro.javadev.sql.library.render.RendererContext;

import java.util.List;

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

}
