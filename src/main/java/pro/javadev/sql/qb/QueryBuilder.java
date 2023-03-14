package pro.javadev.sql.qb;

import pro.javadev.sql.SQLDialect;
import pro.javadev.sql.ast.ASTNode;

public class QueryBuilder {

    private ASTNode root;

    public QueryBuilder(ASTNode root) {
        this.root = root;
    }

    public String interpret(SQLDialect dialect) {
        return root.interpret(dialect);
    }

}
