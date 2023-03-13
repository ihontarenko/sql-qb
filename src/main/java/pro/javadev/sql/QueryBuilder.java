package pro.javadev.sql;

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
