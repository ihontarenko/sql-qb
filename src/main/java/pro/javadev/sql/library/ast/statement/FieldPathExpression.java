package pro.javadev.sql.library.ast.statement;

import pro.javadev.sql.library.ast.ASTNode;

public class FieldPathExpression extends ASTNode {

    private String table;
    private String field;

    public FieldPathExpression(String table, String field) {
        this.table = table;
        this.field = field;
    }

    public FieldPathExpression() {
    }


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "%s: [TABLE: '%s', FIELD: '%s']".formatted(name(), table, field);
    }
}
