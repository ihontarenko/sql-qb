package pro.javadev.sql.v3;

public class Examples {

    public static void main(String[] args) {
        RendererFactory.registerRenderer(SQLDialect.MYSQL, SelectNode.class, node -> node.interpret(SQLDialect.MYSQL));
        RendererFactory.registerRenderer(SQLDialect.MYSQL, LimitNode.class, node -> "LIMIT " + node.getLimit());

        RendererFactory.registerRenderer(SQLDialect.ORACLE, SelectNode.class, node -> node.interpret(SQLDialect.ORACLE));
        RendererFactory.registerRenderer(SQLDialect.ORACLE, LimitNode.class, node -> {
            if (node.getOffset() == 0) {
                return "FETCH FIRST " + node.getLimit() + " ROWS ONLY";
            } else {
                return "OFFSET " + node.getOffset() + " ROWS FETCH NEXT " + node.getLimit() + " ROWS ONLY";
            }
        });

        RendererFactory.registerRenderer(SQLDialect.POSTGRESQL, SelectNode.class, node -> node.interpret(SQLDialect.POSTGRESQL));
        RendererFactory.registerRenderer(SQLDialect.POSTGRESQL, LimitNode.class, node -> {
            if (node.getOffset() == 0) {
                return "LIMIT " + node.getLimit();
            } else {
                return "LIMIT " + node.getLimit() + " OFFSET " + node.getOffset();
            }
        });

        RendererFactory.registerRenderer(SQLDialect.MSSQL, SelectNode.class, node -> node.interpret(SQLDDialect.MSSQL));
        RendererFactory.registerRenderer(SQLDialect.MSSQL, LimitNode.class, node -> {
            if (node.getOffset() == 0) {
                return "TOP " + node.getLimit();
            } else {
                throw new UnsupportedOperationException("OFFSET is not supported in Microsoft SQL Server");
            }
        });

        LimitNode limitNode = new LimitNode(10, 5);
        Renderer renderer = RendererFactory.getRenderer(SQLDialect.MYSQL, LimitNode.class);
        String sql = renderer.render(limitNode); // "LIMIT 10, 5"



    }

}
