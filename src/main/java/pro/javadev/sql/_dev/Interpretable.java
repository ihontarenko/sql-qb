package pro.javadev.sql._dev;

import pro.javadev.sql.v1.render.RendererContext;
import pro.javadev.sql.v1.dialect.Dialect;

@FunctionalInterface
public interface Interpretable {
    String interpret(Dialect dialect, RendererContext context);
}
