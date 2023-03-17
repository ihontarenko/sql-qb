package pro.javadev.sql.library.ast.statement;

import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.ast.IdentifierNode;

public class AliasExpression extends ASTNode {

    public IdentifierNode getIdentifier() {
        return getChild(IdentifierNode.class);
    }

}
