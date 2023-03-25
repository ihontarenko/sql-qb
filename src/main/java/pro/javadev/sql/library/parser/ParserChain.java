package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.node.Node;
import pro.javadev.sql.library.tokenizer.Tokenizer;

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
