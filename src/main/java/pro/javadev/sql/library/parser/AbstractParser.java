package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.token.DefaultToken;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.token.DefaultToken.T_CLOSE_BRACE;
import static pro.javadev.sql.library.token.DefaultToken.T_OPEN_BRACE;

public abstract class AbstractParser<N extends ASTNode> implements Parser<N> {

    protected <T extends ASTNode> T uncover(Parser<T> parser, SQLDialect dialect, ParserContext ctx, Tokenizer tokenizer) {
        T expression;

        shift(T_OPEN_BRACE, tokenizer);
        expression = parser.parse(dialect, ctx, tokenizer);
        shift(T_CLOSE_BRACE, tokenizer);

        return expression;
    }

    protected Token.Entry getCurrentToken(Token expected, Tokenizer tokenizer) {
        Token.Entry current = tokenizer.current();

        if (current.is(expected)) {
            tokenizer.next();
            return current;
        }

        throw new ParserException("EXPECTED TOKEN: [" + expected + "] BUT GOTTEN: [" + current + "]");
    }

    protected void shift(Token expected, Tokenizer tokenizer) {
        shift(expected, tokenizer, true);
    }

    protected void shift(Token expected, Tokenizer tokenizer, boolean throwException) {
        Token.Entry current = tokenizer.current();

        if (current.is(expected)) {
            tokenizer.next();
        } else if (throwException) {
            throw new ParserException("EXPECTED TOKEN: [" + expected + "] BUT GOTTEN: [" + current + "]");
        }
    }

    protected boolean isCurrent(Token expected, Tokenizer tokenizer) {
        return tokenizer.isCurrent(expected);
    }

    protected void moveForward(Token token, Tokenizer tokenizer) {
        if (isCurrent(token, tokenizer)) {
            tokenizer.next();
        }
    }

}
