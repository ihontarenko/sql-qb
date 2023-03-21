package pro.javadev.sql.library.ast;

public class ArithmeticOperator extends ASTNode {

    private String operator;

    public ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(name(), operator);
    }
}
