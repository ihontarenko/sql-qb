package pro.javadev.sql;

import pro.javadev.sql.dialect.Dialect;

@FunctionalInterface
public interface Interpretable {
    String interpret(Dialect dialect, RendererContext context);
}
