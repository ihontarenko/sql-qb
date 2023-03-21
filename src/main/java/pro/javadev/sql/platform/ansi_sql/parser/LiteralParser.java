package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.LiteralNode;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ExpressionRecognizer;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.SQLDialect.ANSI_SQL;

public class LiteralParser extends AbstractParser<LiteralNode> {

    @Override
    public LiteralNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        LiteralNode          node       = new LiteralNode(null);
        ExpressionRecognizer recognizer = context.getExpressionRecognizer(ANSI_SQL);

        if (recognizer.isLiteralExpression(tokenizer)) {
            node.setValue(tokenizer.next().value());
        }

        return node;
    }

}

