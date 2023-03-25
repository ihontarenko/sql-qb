package pro.javadev.sql.library.ast;

public class FieldPathExpression extends ASTNode {

    public FieldPathExpression() {
    }

    public TableIdentifier getTable() {
        return getChild(TableIdentifier.class);
    }

    public void setTable(TableIdentifier table) {
        this.add(table);
    }

    public AliasExpression.FieldIdentifier getField() {
        return getChild(AliasExpression.FieldIdentifier.class);
    }

    public void setField(AliasExpression.FieldIdentifier field) {
        this.add(field);
    }

}
