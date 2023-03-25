package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ExpressionItem;
import pro.javadev.sql.library.ast.FunctionCallExpression;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ExpressionRecognizer;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.token.DefaultToken.*;

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
