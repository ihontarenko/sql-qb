package pro.javadev.sql._dev;

import pro.javadev.sql.ASTNode;
import pro.javadev.sql.dialect.Dialect;

public class QueryBuilder {

    private ASTNode root;

    public QueryBuilder(ASTNode root) {
        this.root = root;
    }

    public String interpret(Dialect dialect) {
        return root.interpret(dialect);
    }

}
