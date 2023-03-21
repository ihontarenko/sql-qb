package pro.javadev.sql.library.ast;

import pro.javadev.sql.platform.ansi_sql.parser.FieldIdentifier;
import pro.javadev.sql.platform.ansi_sql.parser.TableIdentifier;

public class FieldPathExpression extends ASTNode {

    public FieldPathExpression() {
    }

    public TableIdentifier getTable() {
        return getChild(TableIdentifier.class);
    }

    public void setTable(TableIdentifier table) {
        this.add(table);
    }

    public FieldIdentifier getField() {
        return getChild(FieldIdentifier.class);
    }

    public void setField(FieldIdentifier field) {
        this.add(field);
    }

}
