package pro.javadev.sql.platform.ansi_sql.render;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.render.Renderer;
import pro.javadev.sql.library.render.RendererContext;
import pro.javadev.sql.library.ast.ColumnItem;
import pro.javadev.sql.library.ast.SelectStatement;

public class SelectStatementRenderer implements Renderer<SelectStatement> {

    @Override
    public String render(SQLDialect dialect, RendererContext context, SelectStatement node) {
        StringBuilder builder = new StringBuilder("SELECT ");

        for (ColumnItem column : node.getColumns()) {
            builder.append(column.interpret(dialect, context));
        }

//        builder.append("FROM ").append(
//                node.getFrom().interpret(dialect, context)
//        );

        return builder.toString();
    }

}
