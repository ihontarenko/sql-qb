package pro.javadev.sql;

import pro.javadev.sql.dialect.Dialect;

import java.util.Collections;
import java.util.List;

public class TokenPattern {

    private final String    pattern;
    private final TokenType type;
    private final List<Dialect> dialects;

    public TokenPattern(String pattern, TokenType type) {
        this(pattern, type, Collections.emptyList());
    }

    public TokenPattern(String pattern, TokenType type, List<Dialect> dialects) {
        this.pattern = pattern;
        this.type = type;
        this.dialects = dialects;
    }

    public String getPattern() {
        return pattern;
    }

    public TokenType getType() {
        return type;
    }

    public List<Dialect> getDialects() {
        return dialects;
    }

    public boolean supportsDialect(Dialect dialect) {
        return dialects.isEmpty() || dialects.contains(dialect);
    }

}
