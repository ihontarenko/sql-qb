package pro.javadev.sql.v3;

package renderers;

import ast.ASTNode;
import ast.LimitNode;
import dialects.SQLDialect;

public class OracleLimitRenderer implements Renderer {
    private final LimitNode node;

    public OracleLimitRenderer(LimitNode node) {
        this.node = node;
    }

    @Override
    public String render() {
        if (node.getOffset() == 0) {
            return "FETCH FIRST " + node.getLimit() + " ROWS ONLY";
        } else {
            return "OFFSET " + node.getOffset() + " ROWS FETCH NEXT " + node.getLimit() + " ROWS ONLY";
        }
    }
}
