package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.IdentifierNode;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.token.Token;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

import static pro.javadev.sql.internal.token.DefaultToken.T_IDENTIFIER;

public class IdentifierParser extends AbstractParser<IdentifierNode> {

    @Override
    public IdentifierNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        IdentifierNode identifier = new IdentifierNode();
        Token.Entry    entry      = getCurrentToken(T_IDENTIFIER, tokenizer);

        identifier.setIdentifier(entry.value());

        return identifier;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isIdentifier(tokenizer);
    }

}
