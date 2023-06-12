package pro.javadev.sql.internal.ast;

public class LimitNode extends ASTNode {

    private int limit;

    public LimitNode(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "%s: [%d]".formatted(name(), limit);
    }
}
