package pro.javadev.sql.library.ast;

public class ColumnItem extends ASTNode {

    public AliasExpression getAlias() {
        return getChild(AliasExpression.class);
    }

}
