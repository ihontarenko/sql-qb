package pro.javadev.sql.v1.qb;

import pro.javadev.sql.v1.ASTNode;
import pro.javadev.sql.v1.dialect.Dialect;

public class QueryBuilder {

    private ASTNode root;

    public QueryBuilder(ASTNode root) {
        this.root = root;
    }

    public String interpret(Dialect dialect) {
        return root.interpret(dialect);
    }

}
