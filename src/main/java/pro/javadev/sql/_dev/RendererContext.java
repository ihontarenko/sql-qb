package pro.javadev.sql._dev;

import pro.javadev.sql.ASTNode;
import pro.javadev.sql.dialect.Dialect;

import java.util.HashMap;
import java.util.Map;

public class RendererContext {

    private final Dialect                                                  dialect;
    private final Map<Class<? extends ASTNode>, Map<Dialect, Renderer<?>>> renderers;

    public RendererContext(Dialect dialect) {
        this.dialect = dialect;
        this.renderers = new HashMap<>();
    }

    public Dialect getDialect() {
        return dialect;
    }

    public <T extends ASTNode> void registerRenderer(Class<T> nodeClass, Renderer<T> renderer) {
        Map<Dialect, Renderer<?>> renderers = this.renderers.computeIfAbsent(nodeClass, k -> new HashMap<>());
        renderers.put(dialect, renderer);
    }

    public <N extends ASTNode> Renderer<N> getRenderer(Dialect dialect, Class<N> nodeClass) {
        Map<Dialect, Renderer<?>> renderers = this.renderers.get(nodeClass);

        if (renderers != null) {
            return (Renderer<N>) renderers.get(dialect);
        }

        throw new RuntimeException("NO RENDERER FOUND FOR NODE " + nodeClass.getSimpleName() + " AND DIALECT " + dialect);
    }

    public <N extends ASTNode> Renderer<N> getRenderer(Class<N> nodeClass) {
        return getRenderer(dialect, nodeClass);
    }

}
