package pro.javadev.sql.internal.qb;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ASTNode;

public class QueryBuilder {

    private ASTNode root;

    public QueryBuilder(ASTNode root) {
        this.root = root;
    }

    public String interpret(SQLDialect dialect) {
        return root.interpret(dialect, null);
    }

}
