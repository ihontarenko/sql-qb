package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.IdentifierNode;
import pro.javadev.sql.internal.ast.AliasExpression;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.Parser;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

import static pro.javadev.sql.platform.ansi_sql.SQLToken.T_SQL_AS;

public class AliasExpressionParser extends AbstractParser<AliasExpression> {

    @Override
    public AliasExpression parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        AliasExpression        alias  = new AliasExpression();
        Parser<IdentifierNode> parser = context.getParser(dialect, IdentifierNode.class);

        shift(T_SQL_AS, tokenizer, false);

        alias.add(parser.parse(dialect, context, tokenizer));

        return alias;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_SQL_AS);
    }

}
