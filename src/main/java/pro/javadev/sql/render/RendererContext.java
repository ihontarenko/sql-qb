package pro.javadev.sql.render;

import pro.javadev.sql.ast.ASTNode;
import pro.javadev.sql.Context;
import pro.javadev.sql.SQLDialect;

import java.util.HashMap;
import java.util.Map;

public interface RendererContext extends Context {

    SQLDialect getDialect();

    void setDialect(SQLDialect SQLDialect);

    <T extends ASTNode> void registerRenderer(SQLDialect SQLDialect, Class<T> nodeClass, Renderer<T> renderer);

    <T extends ASTNode> void registerRenderer(Class<T> nodeClass, Renderer<T> renderer);

    <N extends ASTNode> Renderer<N> getRenderer(SQLDialect SQLDialect, Class<N> nodeClass);

    <N extends ASTNode> Renderer<N> getRenderer(Class<N> nodeClass);

    Map<Class<? extends ASTNode>, Map<SQLDialect, Renderer<?>>> getRenderers();

    class DefaultRendererContext extends Context.DefaultContext implements RendererContext {

        private static final String SQLDialect_PROPERTY   = "SQLDialect_PROPERTY";
        private static final String RENDERERS_PROPERTY = "RENDERERS_PROPERTY";

        public DefaultRendererContext(SQLDialect SQLDialect) {
            setProperty(SQLDialect_PROPERTY, SQLDialect);
            setProperty(RENDERERS_PROPERTY, new HashMap<>());
        }

        public DefaultRendererContext() {
            this(null);
        }

        @Override
        public SQLDialect getDialect() {
            return getProperty(SQLDialect_PROPERTY);
        }

        @Override
        public void setDialect(SQLDialect SQLDialect) {
            setProperty(SQLDialect_PROPERTY, SQLDialect);
        }

        @Override
        public <T extends ASTNode> void registerRenderer(SQLDialect SQLDialect, Class<T> nodeClass, Renderer<T> renderer) {
            Map<SQLDialect, Renderer<?>> renderers = getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>());
            renderers.put(SQLDialect, renderer);
        }

        @Override
        public <T extends ASTNode> void registerRenderer(Class<T> nodeClass, Renderer<T> renderer) {
            registerRenderer(getDialect(), nodeClass, renderer);
        }

        @Override
        public <N extends ASTNode> Renderer<N> getRenderer(SQLDialect SQLDialect, Class<N> nodeClass) {
            Map<SQLDialect, Renderer<?>> renderers = getRenderers().computeIfAbsent(nodeClass, k -> new HashMap<>());

            if (renderers != null) {
                return (Renderer<N>) renderers.get(SQLDialect);
            }

            throw new RuntimeException("NO RENDERER FOUND FOR NODE " + nodeClass.getSimpleName() + " AND SQLDialect " + SQLDialect);
        }

        @Override
        public <N extends ASTNode> Renderer<N> getRenderer(Class<N> nodeClass) {
            return getRenderer(getDialect(), nodeClass);
        }

        @Override
        public Map<Class<? extends ASTNode>, Map<SQLDialect, Renderer<?>>> getRenderers() {
            return getProperty(RENDERERS_PROPERTY);
        }

    }

}
