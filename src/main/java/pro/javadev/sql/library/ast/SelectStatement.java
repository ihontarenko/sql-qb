package pro.javadev.sql.library.ast;

import java.util.List;

public class SelectStatement extends ASTNode {

    public List<ColumnItem> getColumns() {
        return getChildren(ColumnItem.class);
    }

    public void removeColumns() {
        for (ColumnItem column : getColumns()) {
            delete(column);
        }
    }

    public FromClause getFrom() {
        return getChild(FromClause.class);
    }

    public WhereClause getWhere() {
        return getChild(WhereClause.class);
    }

    public List<GroupByClause> getGroupBy() {
        return getChildren(GroupByClause.class);
    }

    public HavingClause getHaving() {
        return getChild(HavingClause.class);
    }

    public List<OrderByClause> getOrderBy() {
        return getChildren(OrderByClause.class);
    }

    public int getLimit() {
        LimitNode limit = getChild(LimitNode.class);
        return limit == null ? 0 : limit.getLimit();
    }

    public int getOffset() {
        OffsetNode offset = getChild(OffsetNode.class);
        return offset == null ? 0 : offset.getOffset();
    }

}

