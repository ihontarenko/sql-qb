package pro.javadev.sql.library.render;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.node.Node;

public interface Renderer<N extends Node> {
    String render(SQLDialect dialect, RendererContext context, N node);
}
