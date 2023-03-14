package pro.javadev.sql.lexer;

import pro.javadev.sql.SQLDialect;
import pro.javadev.sql.common.Token;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class TokenPattern {

    private final Pattern          pattern;
    private final Token            token;
    private final List<SQLDialect> dialects;

    public TokenPattern(String pattern, Token type) {
        this(pattern, type, Collections.emptyList());
    }

    public TokenPattern(String pattern, Token token, List<SQLDialect> dialects) {
        this.pattern = Pattern.compile(pattern);
        this.token = token;
        this.dialects = dialects;
    }

    public List<SQLDialect> getDialects() {
        return dialects;
    }

    public boolean supportsDialect(SQLDialect dialect) {
        return dialects.isEmpty() || dialects.contains(dialect);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Token getToken() {
        return token;
    }

}
