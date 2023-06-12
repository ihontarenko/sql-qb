package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ExpressionItem;
import pro.javadev.sql.internal.ast.FunctionCallExpression;
import pro.javadev.sql.internal.ast.IdentifierNode;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.Parser;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

import static pro.javadev.sql.internal.token.DefaultToken.*;

public class FunctionCallParser extends AbstractParser<FunctionCallExpression> {

    @Override
    public FunctionCallExpression parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        FunctionCallExpression expression = new FunctionCallExpression();
        Parser<ExpressionItem> parser = context.getParser(dialect, ExpressionItem.class);

        expression.add(context.getParser(dialect, IdentifierNode.class).parse(dialect, context, tokenizer));

        shift(T_OPEN_BRACE, tokenizer);

        expression.add(parser.parse(dialect, context, tokenizer));
        while (tokenizer.isCurrent(T_COMMA)) {
            shift(T_COMMA, tokenizer);
            expression.add(parser.parse(dialect, context, tokenizer));
        }

        shift(T_CLOSE_BRACE, tokenizer);

        return expression;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isFunctionExpression(tokenizer);
    }

}
