package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.ast.AliasExpression;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.SQLToken;

import static pro.javadev.sql.library.token.DefaultToken.T_IDENTIFIER;
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

}
