package pro.javadev.sql.platform.ansi_sql.ast.statement;

import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.platform.mysql.ast.LimitNode;
import pro.javadev.sql.platform.mysql.ast.OffsetNode;

import java.util.List;

public class SelectNode extends ASTNode {

    public List<ColumnsNode> getColumns() {
        return getChildren(ColumnsNode.class);
    }

    public void removeColumns() {
        for (ColumnsNode column : getColumns()) {
            delete(column);
        }
    }

    public FromNode getFrom() {
        return getChild(FromNode.class);
    }

    public WhereNode getWhere() {
        return getChild(WhereNode.class);
    }

    public List<GroupByNode> getGroupBy() {
        return getChildren(GroupByNode.class);
    }

    public HavingNode getHaving() {
        return getChild(HavingNode.class);
    }

    public List<OrderByNode> getOrderBy() {
        return getChildren(OrderByNode.class);
    }

    public int getLimit() {
        LimitNode limit = getChild(LimitNode.class);
        return limit == null ? 0 : limit.getLimit();
    }

    public int getOffset() {
        OffsetNode offset = getChild(OffsetNode.class);
        return offset == null ? 0 : offset.getOffset();
    }

    @Override
    public String toString() {
        return "SELECT_NODE[children=%s]".formatted(children);
    }
}

