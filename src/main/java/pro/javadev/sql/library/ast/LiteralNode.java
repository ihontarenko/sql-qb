package pro.javadev.sql.library.ast;

public class LiteralNode extends ASTNode {

    private Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(name(), value);
    }

}
