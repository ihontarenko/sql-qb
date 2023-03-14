package pro.javadev.sql.v1.render;

import pro.javadev.sql.v1.ASTNode;
import pro.javadev.sql.v1.Context;
import pro.javadev.sql.v1.dialect.Dialect;

import java.util.HashMap;
import java.util.Map;

public interface RendererContext extends Context {

    Dialect getDialect();

    void setDialect(Dialect dialect);

    <T extends ASTNode> void registerRenderer(Dialect dialect, Class<T> nodeClass, Renderer<T> renderer);

    <T extends ASTNode> void registerRenderer(Class<T> nodeClass, Renderer<T> renderer);

    <N extends ASTNode> Renderer<N> getRenderer(Dialect dialect, Class<N> nodeClass);

    <N extends ASTNode> Renderer<N> getRenderer(Class<N> nodeClass);

    Map<Class<? extends ASTNode>, Map<Dialect, Renderer<?>>> getRenderers();

    class DefaultRendererContext extends Context.DefaultContext implements RendererContext {

        private static final String DIALECT_PROPERTY   = "DIALECT_PROPERTY";
        private static final String RENDERERS_PROPERTY = "RENDERERS_PROPERTY";

        public DefaultRendererContext(Dialect dialect) {
            setProperty(DIALECT_PROPERTY, dialect);
            setProperty(RENDERERS_PROPERTY, new HashMap<>());
        }

        public DefaultRendererContext() {
            this(null);
        }

        @Override
        public Dialect getDialect() {
            return getProperty(DIALECT_PROPERTY);
        }

        @Override
        public void setDialect(Dialect dialect) {
            setProperty(DIALECT_PROPERTY, dialect);
        }

        @Override
        public <T extends ASTNode> void registerRenderer(Dialect dialect, Class<T> nodeClass, Renderer<T> renderer) {
            Map<Dialect, Renderer<?>> renderers = getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>());
            renderers.put(dialect, renderer);
        }

        @Override
        public <T extends ASTNode> void registerRenderer(Class<T> nodeClass, Renderer<T> renderer) {
            registerRenderer(getDialect(), nodeClass, renderer);
        }

        @Override
        public <N extends ASTNode> Renderer<N> getRenderer(Dialect dialect, Class<N> nodeClass) {
            Map<Dialect, Renderer<?>> renderers = getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>());

            if (renderers != null) {
                return (Renderer<N>) renderers.get(dialect);
            }

            throw new RuntimeException("NO RENDERER FOUND FOR NODE " + nodeClass.getSimpleName() + " AND DIALECT " + dialect);
        }

        @Override
        public <N extends ASTNode> Renderer<N> getRenderer(Class<N> nodeClass) {
            return getRenderer(getDialect(), nodeClass);
        }

        @Override
        public Map<Class<? extends ASTNode>, Map<Dialect, Renderer<?>>> getRenderers() {
            return getProperty(RENDERERS_PROPERTY);
        }

    }

}
