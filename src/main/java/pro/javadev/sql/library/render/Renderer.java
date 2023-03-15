package pro.javadev.sql.library.render;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

public interface Renderer<N extends ASTNode> {
    String render(SQLDialect dialect, RendererContext context, N node);
}
