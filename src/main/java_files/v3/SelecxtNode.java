package pro.javadev.sql.v3;

import java.util.List;

public class SelectNode extends ASTNode {
    public SelectNode(List<ASTNode> children) {
        this.children = children;

        if (getColumns().isEmpty()) {
            throw new IllegalArgumentException("Columns are required for SELECT statement");
        }
    }

    public List<ASTNode> getColumns() {
        return getChildrenList(ColumnsNode.class);
    }

    public ASTNode getFrom() {
        return getChild(FromNode.class);
    }

    public ASTNode getWhere() {
        return getChild(WhereNode.class);
    }

    public List<ASTNode> getGroupBy() {
        return getChildrenList(GroupByNode.class);
    }

    public ASTNode getHaving() {
        return getChild(HavingNode.class);
    }

    public List<ASTNode> getOrderBy() {
        return getChildrenList(OrderByNode.class);
    }

    public int getLimit() {
        LimitNode limitNode = getChild(LimitNode.class);
        if (limitNode == null) {
            return 0;
        } else {
            return limitNode.getLimit();
        }
    }

    public int getOffset() {
        OffsetNode offsetNode = getChild(OffsetNode.class);
        if (offsetNode == null) {
            return 0;
        } else {
            return offsetNode.getOffset();
        }
    }

    @Override
    public String interpret(SQLDialect dialect) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");

        List<ASTNode> columns = getColumns();

        if (columns.isEmpty()) {
            sql.append("*");
        } else {
            for (int i = 0; i < columns.size(); i++) {
                ASTNode column = columns.get(i);
                sql.append(column.interpret(dialect));

                if (i < columns.size() - 1) {
                    sql.append(", ");
                }
            }
        }

        ASTNode from = getFrom();

        sql.append(" FROM ");
        sql.append(from.interpret(dialect));

        ASTNode where = getWhere();

        if (where != null) {
            sql.append(" WHERE ");
            sql.append(where.interpret(dialect));
        }

        List<ASTNode> groupBy = getGroupBy();

        if (!groupBy.isEmpty()) {
            sql.append(" GROUP BY ");
            for (int i = 0; i < groupBy.size(); i++) {
                ASTNode group = groupBy.get(i);
                sql.append(group.interpret(dialect));

                if (i
                sql.append(", ");
            }
        }

        ASTNode having = getHaving();

        if (having != null) {
            sql.append(" HAVING ");
            sql.append(having.interpret(dialect));
        }

        List<ASTNode> orderBy = getOrderBy();

        if (!orderBy.isEmpty()) {
            sql.append(" ORDER BY ");
            for (int i = 0; i < orderBy.size(); i++) {
                ASTNode order = orderBy.get(i);
                sql.append(order.interpret(dialect));

                if (i < orderBy.size() - 1) {
                    sql.append(", ");
                }
            }
        }

        int limit = getLimit();
        int offset = getOffset();

        if (dialect == SQLDialect.MYSQL) {
            if (limit > 0) {
                sql.append(" LIMIT ");
                sql.append(limit);

                if (offset > 0) {
                    sql.append(" OFFSET ");
                    sql.append(offset);
                }
            }
        } else if (dialect == SQLDialect.POSTGRESQL) {
            if (limit > 0) {
                sql.append(" LIMIT ");
                sql.append(limit);

                if (offset > 0) {
                    sql.append(" OFFSET ");
                    sql.append(offset);
                }
            }
        } else if (dialect == SQLDialect.ORACLE) {
            if (limit > 0) {
                sql.insert(0, "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( ");
                sql.append(" ) a WHERE ROWNUM <= " + (offset + limit) + " ) WHERE rnum > " + offset);
            }
        } else if (dialect == SQLDialect.MSSQL) {
            if (limit > 0) {
                if (getOrderBy().isEmpty()) {
                    throw new IllegalArgumentException("ORDER BY clause is required when using LIMIT for Microsoft SQL Server");
                }

                sql.insert(0, "SELECT TOP " + limit + " * FROM ( ");
                sql.append(" ) AS TMP");

                if (offset > 0) {
                    sql.append(" WHERE TMP.__row > " + offset);
                }

                String orderBy = orderByToString(dialect);
                sql.append(" ORDER BY " + orderBy);
            }
        }

        return sql.toString();
    }

    private String orderByToString(SQLDialect dialect) {
        List<ASTNode> orderBy = getOrderBy();

        if (orderBy.isEmpty()) {
            throw new IllegalArgumentException("ORDER BY clause is required");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < orderBy.size(); i++) {
            ASTNode order = orderBy.get(i);
            sb.append(order.interpret(dialect));

            if (i < orderBy.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
