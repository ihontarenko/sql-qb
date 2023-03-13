package pro.javadev.sql;

import java.util.HashMap;
import java.util.Map;

public class RendererFactory {
    private final SQLDialect dialect;
    private final Map<Class<? extends Node>, Map<SQLDialect, Class<? extends Renderer>>> rendererMap = new HashMap<>();

    public RendererFactory(SQLDialect dialect) {
        this.dialect = dialect;
    }

    public <N extends Node, R extends Renderer<N>> void registerRenderer(Class<R> rendererClass, Class<N> nodeClass) {
        Map<SQLDialect, Class<? extends Renderer>> dialectRendererMap = rendererMap.computeIfAbsent(nodeClass, k -> new HashMap<>());
        dialectRendererMap.put(dialect, rendererClass);
    }

    public <N extends Node> void setRenderer(Class<? extends Renderer<N>> rendererClass, Class<N> nodeClass) {
        Map<SQLDialect, Class<? extends Renderer>> dialectRendererMap = rendererMap.computeIfAbsent(nodeClass, k -> new HashMap<>());
        dialectRendererMap.put(dialect, rendererClass);
    }

    public <N extends Node> Renderer<N> getRenderer(Class<N> nodeClass) {
        Map<SQLDialect, Class<? extends Renderer>> dialectRendererMap = rendererMap.get(nodeClass);
        if (dialectRendererMap != null) {
            Class<? extends Renderer> rendererClass = dialectRendererMap.get(dialect);
            if (rendererClass != null) {
                try {
                    return rendererClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create instance of renderer", e);
                }
            }
        }
        throw new RuntimeException("No renderer found for node " + nodeClass.getSimpleName() + " and dialect " + dialect);
    }

    public <N extends Node> Renderer<N> getRenderer(N node) {
        return getRenderer(node.getClass());
    }

    public <N extends Node> Renderer<N> getRenderer(SQLDialect dialect, Class<N> nodeClass) {
        Map<SQLDialect, Class<? extends Renderer>> dialectRendererMap = rendererMap.get(nodeClass);
        if (dialectRendererMap != null) {
            Class<? extends Renderer> rendererClass = dialectRendererMap.get(dialect);
            if (rendererClass != null) {
                try {
                    return rendererClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create instance of renderer", e);
                }
            }
        }
        throw new RuntimeException("No renderer found for node " + nodeClass.getSimpleName() + " and dialect " + dialect);
    }

    public <N extends Node> Renderer<N> getRenderer(SQLDialect dialect, N node) {
        return getRenderer(dialect, node.getClass());
    }
}

