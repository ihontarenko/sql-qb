package pro.javadev.sql.library.ast.statement;

import pro.javadev.sql.library.ast.ASTNode;

public class ColumnItem extends ASTNode {

    public AliasExpression getAlias() {
        return getChild(AliasExpression.class);
    }

}
