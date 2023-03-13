package pro.javadev.sql;

public class TokenPatternBuilder {
    private Class<? extends Token> tokenClass;
    private Map<TokenType, String> patterns = new HashMap<>();

    public TokenPatternBuilder(Class<? extends Token> tokenClass) {
        this.tokenClass = tokenClass;
    }

    public TokenPatternBuilder addPattern(TokenType type, String regex) {
        patterns.put(type, regex);
        return this;
    }

    public TokenPattern build() {
        return new TokenPattern(tokenClass, patterns);
    }
}
