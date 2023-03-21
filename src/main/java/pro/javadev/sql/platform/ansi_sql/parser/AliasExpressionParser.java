package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.ast.AliasExpression;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.token.DefaultToken.T_IDENTIFIER;

public class AliasExpressionParser extends AbstractParser<AliasExpression> {

    @Override
    public AliasExpression parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        AliasExpression        alias  = new AliasExpression();
        Parser<IdentifierNode> parser = context.getParser(dialect, IdentifierNode.class);

        tokenizer.forward(T_IDENTIFIER);
        alias.add(parser.parse(dialect, context, tokenizer));

        return alias;
    }

}
