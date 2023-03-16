package pro.javadev.sql.platform.ansi_sql.render;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.render.Renderer;
import pro.javadev.sql.library.render.RendererContext;
import pro.javadev.sql.platform.ansi_sql.ast.statement.ColumnsNode;
import pro.javadev.sql.platform.ansi_sql.ast.statement.SelectNode;

public class SelectNodeRenderer implements Renderer<SelectNode> {

    @Override
    public String render(SQLDialect dialect, RendererContext context, SelectNode node) {
        StringBuilder builder = new StringBuilder("SELECT ");

        for (ColumnsNode column : node.getColumns()) {
            builder.append(column.interpret(dialect, context));
        }

//        builder.append("FROM ").append(
//                node.getFrom().interpret(dialect, context)
//        );

        return builder.toString();
    }

}
