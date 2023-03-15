package pro.javadev.sql.library.ast;

public class ColumnsNode extends ASTNode {

    private String expression;

    public ColumnsNode(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "COLUMNS_NODE: [expression='%s']".formatted(expression);
    }
}
