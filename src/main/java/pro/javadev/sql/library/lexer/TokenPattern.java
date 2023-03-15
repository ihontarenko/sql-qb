package pro.javadev.sql.library.lexer;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.common.Priority;
import pro.javadev.sql.library.token.Token;

import java.util.regex.Pattern;

public class TokenPattern implements Priority {

    private final Pattern    pattern;
    private final Token      token;
    private final SQLDialect dialect;
    private final int        priority;

    public TokenPattern(String pattern, Token type) {
        this(pattern, type, null, type.type());
    }

    public TokenPattern(String pattern, Token type, int priority) {
        this(pattern, type, null, priority);
    }

    public TokenPattern(String pattern, Token token, SQLDialect dialect, int priority) {
        this.pattern = Pattern.compile(pattern);
        this.token = token;
        this.dialect = dialect;
        this.priority = priority;
    }

    public SQLDialect getDialect() {
        return dialect;
    }

    public boolean supportsDialect(SQLDialect dialect) {
        return dialect == null || this.dialect == dialect;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return "TOKEN_PATTERN[%s] [%d] %s -> %s"
                .formatted(dialect, priority, pattern, token);
    }
}
