package pro.javadev.sql.library.ast;

public class AliasExpression extends ASTNode {

    public IdentifierNode getIdentifier() {
        return getChild(IdentifierNode.class);
    }

    public static class FieldIdentifier extends IdentifierNode {

        public FieldIdentifier(String identifier) {
            super(identifier);
        }

    }
}
