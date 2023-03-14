package pro.javadev.sql.v1.matcher;

import pro.javadev.sql.v1.common.Token;
import pro.javadev.sql.v1.dialect.Dialect;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PatternTokenMatcher implements TokenMatcher<String, Token> {

    private final Pattern       pattern;
    private final Token         type;
    private final List<Dialect> dialects;

    public PatternTokenMatcher(String pattern, Token type) {
        this(pattern, type, Collections.emptyList());
    }

    public PatternTokenMatcher(String pattern, Token type, List<Dialect> dialects) {
        this.pattern = Pattern.compile(pattern);
        this.type = type;
        this.dialects = dialects;
    }

    public List<Dialect> getDialects() {
        return dialects;
    }

    public boolean supportsDialect(Dialect dialect) {
        return dialects.isEmpty() || dialects.contains(dialect);
    }

    @Override
    public Token match(String value) {
        return null;
    }

}
