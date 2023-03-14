package pro.javadev.sql.render;

import pro.javadev.sql.common.node.Node;

public interface Renderer<N extends Node> {
    String render(N node, RendererContext context);
}
