package pro.javadev.sql.v3;

import java.util.HashMap;
import java.util.Map;

public class RendererFactory {
    private static final Map<SQLDialect, Map<Class<? extends ASTNode>, Renderer>> renderers = new HashMap<>();

    public static void registerRenderer(SQLDialect dialect, Class<? extends ASTNode> clazz, Renderer renderer) {
        Map<Class<? extends ASTNode>, Renderer> dialectRenderers = renderers.computeIfAbsent(dialect, k -> new HashMap<>());
        dialectRenderers.put(clazz, renderer);
    }

    public static Renderer getRenderer(SQLDialect dialect, Class<? extends ASTNode> clazz) {
        Map<Class<? extends ASTNode>, Renderer> dialectRenderers = renderers.get(dialect);

        if (dialectRenderers == null) {
            throw new IllegalArgumentException("Renderer not found for dialect " + dialect);
        }

        Renderer renderer = dialectRenderers.get(clazz);

        if (renderer == null) {
            throw new IllegalArgumentException("Renderer not found for class " + clazz.getSimpleName());
        }

        return renderer;
    }
}

