package pro.javadev.sql.internal.render;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ASTNode;

public interface Renderer<N extends ASTNode> {
    String render(SQLDialect dialect, RendererContext context, N node);
}
