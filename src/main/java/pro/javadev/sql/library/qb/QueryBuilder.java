package pro.javadev.sql.library.qb;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

public class QueryBuilder {

    private ASTNode root;

    public QueryBuilder(ASTNode root) {
        this.root = root;
    }

    public String interpret(SQLDialect dialect) {
        return root.interpret(dialect, null);
    }

}
