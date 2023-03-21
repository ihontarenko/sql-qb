package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public abstract class AbstractParser<N extends ASTNode> implements Parser<N> {

    protected Token.Entry getCurrentToken(Token expected, Tokenizer tokenizer) {
        Token.Entry current = tokenizer.current();

        if (current.is(expected)) {
            tokenizer.next();
            return current;
        }

        throw new ParserException("EXPECTED TOKEN: [" + expected + "] BUT GOTTEN: [" + current + "]", tokenizer);
    }

    protected void shift(Token expected, Tokenizer tokenizer) {
        shift(expected, tokenizer, true);
    }

    protected void shift(Token expected, Tokenizer tokenizer, boolean throwException) {
        Token.Entry current = tokenizer.current();

        if (current.is(expected)) {
            tokenizer.next();
        } else if (throwException) {
            throw new ParserException("EXPECTED TOKEN: [" + expected + "] BUT GOTTEN: [" + current + "]", tokenizer);
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
