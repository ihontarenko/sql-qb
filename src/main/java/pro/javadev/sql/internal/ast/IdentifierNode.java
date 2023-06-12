package pro.javadev.sql.internal.ast;

public class IdentifierNode extends ASTNode {

    private String identifier;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    public IdentifierNode() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(name(), identifier);
    }
}
