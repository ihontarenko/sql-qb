package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.token.DefaultToken.T_IDENTIFIER;

public class IdentifierParser extends AbstractParser<IdentifierNode> {

    @Override
    public IdentifierNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        IdentifierNode identifier = new IdentifierNode();
        Token.Entry    entry      = consumeToken(T_IDENTIFIER, tokenizer);

        identifier.setIdentifier(entry.value());

        return identifier;
    }

}
