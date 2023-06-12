package pro.javadev.sql.internal.ast;

public class ColumnItem extends ASTNode {

    public AliasExpression getAlias() {
        return getChild(AliasExpression.class);
    }

}
