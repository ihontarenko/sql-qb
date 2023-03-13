package pro.javadev.sql.v3;

package renderers;

import ast.ASTNode;
import ast.SelectNode;
import dialects.SQLDialect;

public class MySQLSelectRenderer implements Renderer {
    private final SelectNode node;

    public MySQLSelectRenderer(SelectNode node) {
        this.node = node;
    }

    @Override
    public String render() {
        StringBuilder sql = new StringBuilder("SELECT ");

        if (node.isDistinct()) {
            sql.append("DISTINCT ");
        }

        ASTNode columns = node.getColumns();

        if (columns == null) {
            throw new IllegalArgumentException("Columns is required for SELECT statement query");
        }

        sql.append(columns.interpret(SQLDialect.MYSQL));

        ASTNode from = node.getFrom();

        if (from != null) {
            sql.append(" FROM ");
            sql.append(from.interpret(SQLDialect.MYSQL));
        }

        ASTNode where = node.getWhere();

        if (where != null) {
            sql.append(" WHERE ");
            sql.append(where.interpret(SQLDialect.MYSQL));
        }

        ASTNode groupBy = node.getGroupBy();

        if (groupBy != null) {
            sql.append(" GROUP BY ");
            sql.append(groupBy.interpret(SQLDialect.MYSQL));
        }

        ASTNode having = node.getHaving();

        if (having != null) {
            sql.append(" HAVING ");
            sql.append(having.interpret(SQLDialect.MYSQL));
        }

        ASTNode orderBy = node.getOrderBy();

        if (orderBy != null) {
            sql.append(" ORDER BY ");
            sql.append(orderBy.interpret(SQLDialect.MYSQL));
        }

        int limit = node.getLimit();
        int offset = node.getOffset();

        if (limit > 0) {
            sql.append(" LIMIT ");
            sql.append(limit);

            if (offset > 0) {
                sql.append(" OFFSET ");
                sql.append(offset);
            }
        }

        return sql.toString();
    }
}
