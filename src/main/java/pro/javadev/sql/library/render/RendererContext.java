package pro.javadev.sql.library.render;

import pro.javadev.sql.library.Context;
import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public interface RendererContext extends Context {

    void setDialect(SQLDialect dialect);

    SQLDialect getDialect();

    <N extends ASTNode> void addRenderer(SQLDialect dialect, Class<? extends N> nodeClass, Renderer<N> renderer);

    <N extends ASTNode> Renderer<N> getRenderer(SQLDialect dialect, Class<N> nodeClass);

    Map<SQLDialect, Map<Class<? extends ASTNode>, Renderer<? extends ASTNode>>> getRenderers();

    Map<Class<? extends ASTNode>, Renderer<? extends ASTNode>> getRenderers(SQLDialect dialect);

    class DefaultRendererContext extends Context.DefaultContext implements RendererContext {

        private static final String RENDERERS_PROPERTY = "RENDERERS";
        private static final String DIALECT_PROPERTY   = "DIALECT";

        public DefaultRendererContext() {
            setProperty(RENDERERS_PROPERTY, new HashMap<>());
        }

        @Override
        public void setDialect(SQLDialect dialect) {
            setProperty(DIALECT_PROPERTY, dialect);
        }

        @Override
        public SQLDialect getDialect() {
            return getProperty(DIALECT_PROPERTY);
        }

        @Override
        public <T extends ASTNode> void addRenderer(SQLDialect dialect, Class<? extends T> nodeClass, Renderer<T> renderer) {
            getRenderers().computeIfAbsent(dialect, k -> new HashMap<>()).put(nodeClass, renderer);
        }

        @Override
        public <N extends ASTNode> Renderer<N> getRenderer(SQLDialect dialect, Class<N> nodeClass) {
            Renderer<? extends ASTNode> renderer = getRenderers(dialect).get(nodeClass);

            if (renderer == null) {
                renderer = getRenderers(getDialect()).get(nodeClass);
            }

            if (renderer == null) {
                throw new RendererException("DIALECT: '%s' HAS NO RENDERER FOR NODE TYPE '%s'"
                        .formatted(dialect, nodeClass.getSimpleName()));
            }

            return (Renderer<N>) renderer;
        }

        @Override
        public Map<Class<? extends ASTNode>, Renderer<? extends ASTNode>> getRenderers(SQLDialect dialect) {
            return getRenderers().computeIfAbsent(dialect, k -> new HashMap<>());
        }

        @Override
        public Map<SQLDialect, Map<Class<? extends ASTNode>, Renderer<? extends ASTNode>>> getRenderers() {
            return getProperty(RENDERERS_PROPERTY);
        }

    }

}
