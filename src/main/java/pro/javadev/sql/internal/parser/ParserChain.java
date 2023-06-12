package pro.javadev.sql.internal.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ASTNode;
import pro.javadev.sql.internal.node.Node;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public class ParserChain extends AbstractParser<ASTNode> {

    @Override
    public ASTNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ASTNode              node       = null;
        ExpressionRecognizer recognizer = context.getExpressionRecognizer(dialect);

        for (Node child : children()) {
            Parser<? extends ASTNode> parser = (Parser<? extends ASTNode>) child;
            if (parser.matchable(recognizer, tokenizer)) {
                node = parser.parse(dialect, context, tokenizer);
            }
        }

        return node;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return false;
    }

}
