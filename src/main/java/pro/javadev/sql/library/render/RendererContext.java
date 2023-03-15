package pro.javadev.sql.library.render;

import pro.javadev.sql.library.Context;
import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public interface RendererContext extends Context {

    <N extends ASTNode> void addRenderer(SQLDialect dialect, Class<? extends N> nodeClass, Renderer<N> renderer);

    <N extends ASTNode> Renderer<N> getRenderer(SQLDialect dialect, Class<N> nodeClass);

    Map<Class<? extends ASTNode>, Map<SQLDialect, Renderer<?>>> getRenderers();

    class DefaultRendererContext extends Context.DefaultContext implements RendererContext {

        private static final String RENDERERS_PROPERTY   = "RENDERERS";

        public DefaultRendererContext() {
            setProperty(RENDERERS_PROPERTY, new HashMap<>());
        }

        @Override
        public <T extends ASTNode> void addRenderer(SQLDialect dialect, Class<? extends T> nodeClass, Renderer<T> renderer) {
            getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>()).put(dialect, renderer);
        }


        @Override
        public <N extends ASTNode> Renderer<N> getRenderer(SQLDialect dialect, Class<N> nodeClass) {
            Renderer<N> renderer = (Renderer<N>) getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>()).get(dialect);

            if (renderer != null) {
                return renderer;
            }

            throw new RuntimeException("NO RENDERER FOUND FOR NODE '" + nodeClass.getSimpleName() + "' AND DIALECT: " + dialect);
        }

        @Override
        public Map<Class<? extends ASTNode>, Map<SQLDialect, Renderer<?>>> getRenderers() {
            return getProperty(RENDERERS_PROPERTY);
        }
    }

}
