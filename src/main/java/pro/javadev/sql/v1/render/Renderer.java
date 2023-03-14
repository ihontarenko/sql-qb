package pro.javadev.sql.v1.render;

import pro.javadev.sql.v1.common.node.Node;

public interface Renderer<N extends Node> {
    String render(N node, RendererContext context);
}
