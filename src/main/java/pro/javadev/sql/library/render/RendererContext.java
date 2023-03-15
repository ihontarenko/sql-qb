package pro.javadev.sql.library.render;

import pro.javadev.sql.library.Context;
import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public interface RendererContext extends Context {

    SQLDialect getDialect();

    void setDialect(SQLDialect SQLDialect);

    <T extends ASTNode> void registerRenderer(SQLDialect SQLDialect, Class<? extends T> nodeClass, Renderer<? super T> renderer);

    <T extends ASTNode> void registerRenderer(Class<? extends T> nodeClass, Renderer<? super T> renderer);

    <N extends ASTNode> Renderer<? super N> getRenderer(SQLDialect SQLDialect, N node);

    <N extends ASTNode> Renderer<? super N> getRenderer(SQLDialect SQLDialect, Class<N> nodeClass);

    <N extends ASTNode> Renderer<? super N> getRenderer(Class<N> nodeClass);

    Map<Class<? extends ASTNode>, Map<SQLDialect, Renderer<?>>> getRenderers();

    class DefaultRendererContext extends Context.DefaultContext implements RendererContext {

        private static final String SQL_DIALECT_PROPERTY = "SQL_DIALECT";
        private static final String RENDERERS_PROPERTY   = "RENDERERS";

        public DefaultRendererContext(SQLDialect SQLDialect) {
            setProperty(SQL_DIALECT_PROPERTY, SQLDialect);
            setProperty(RENDERERS_PROPERTY, new HashMap<>());
        }

        public DefaultRendererContext() {
            this(null);
        }

        @Override
        public SQLDialect getDialect() {
            return getProperty(SQL_DIALECT_PROPERTY);
        }

        @Override
        public void setDialect(SQLDialect SQLDialect) {
            setProperty(SQL_DIALECT_PROPERTY, SQLDialect);
        }

        @Override
        public <T extends ASTNode> void registerRenderer(SQLDialect SQLDialect, Class<? extends T> nodeClass, Renderer<? super T> renderer) {
            Map<SQLDialect, Renderer<?>> renderers = getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>());
            renderers.put(SQLDialect, renderer);
        }

        @Override
        public <T extends ASTNode> void registerRenderer(Class<? extends T> nodeClass, Renderer<? super T> renderer) {
            registerRenderer(getDialect(), nodeClass, renderer);
        }

        @Override
        public <N extends ASTNode> Renderer<? super N> getRenderer(SQLDialect dialect, N node) {
            return (Renderer<? super N>) getRenderer(dialect, node.getClass());
        }

        @Override
        public <N extends ASTNode> Renderer<? super N> getRenderer(SQLDialect SQLDialect, Class<N> nodeClass) {
            Map<SQLDialect, Renderer<?>> renderers = getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>());

            if (renderers != null) {
                return (Renderer<? super N>) renderers.get(SQLDialect);
            }

            throw new RuntimeException("NO RENDERER FOUND FOR NODE " + nodeClass.getSimpleName() + " AND SQLDialect " + SQLDialect);
        }

        @Override
        public <N extends ASTNode> Renderer<? super N> getRenderer(Class<N> nodeClass) {
            return (Renderer<? super N>) getRenderer(getDialect(), nodeClass);
        }

        @Override
        public Map<Class<? extends ASTNode>, Map<SQLDialect, Renderer<?>>> getRenderers() {
            return getProperty(RENDERERS_PROPERTY);
        }

    }

}
