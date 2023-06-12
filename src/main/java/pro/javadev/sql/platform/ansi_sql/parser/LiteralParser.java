package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.LiteralNode;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public class LiteralParser extends AbstractParser<LiteralNode> {

    @Override
    public LiteralNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        return new LiteralNode(tokenizer.next().value());
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isLiteralExpression(tokenizer);
    }

}

