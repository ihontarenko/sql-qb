package pro.javadev.sql.library.render;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ColumnsNode;
import pro.javadev.sql.library.ast.SelectNode;

public class SelectNodeRenderer implements Renderer<SelectNode> {

    @Override
    public String render(SQLDialect dialect, RendererContext context, SelectNode node) {
        StringBuilder builder = new StringBuilder();

        for (ColumnsNode column : node.getColumns()) {
            builder.append(column.interpret(dialect, context));
        }

        builder.append("FROM ").append(
                node.getFrom().interpret(dialect, context)
        );

        return builder.toString();
    }

}
