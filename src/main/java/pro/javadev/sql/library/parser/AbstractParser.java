package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.node.AbstractNode;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.common.StringUtils.underscored;
import static pro.javadev.sql.library.token.DefaultToken.T_CLOSE_BRACE;
import static pro.javadev.sql.library.token.DefaultToken.T_OPEN_BRACE;

public abstract class AbstractParser<N extends ASTNode> extends AbstractNode implements Parser<N> {

    @SafeVarargs
    protected final ParserResolver resolver(Class<? extends ASTNode>... classes) {
        return new ParserResolver(classes);
    }

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

        throw parseErrorException(current, expected);
    }

    protected void shift(Token expected, Tokenizer tokenizer) {
        shift(expected, tokenizer, true);
    }

    protected void shift(Token expected, Tokenizer tokenizer, boolean throwException) {
        Token.Entry current = tokenizer.current();

        if (current.is(expected)) {
            tokenizer.next();
        } else if (throwException) {
            throw parseErrorException(current, expected);
        }
    }

    @Override
    public String toString() {
        return underscored(getClass().getSimpleName(), true);
    }

}
