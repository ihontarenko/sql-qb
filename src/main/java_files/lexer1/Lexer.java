package pro.javadev.sql.lexer1;

public class Lexer {
    private SqlDialect sqlDialect = SqlDialect.ANSI;
    private TokenPattern[] patterns;

    public Lexer setSqlDialect(SqlDialect sqlDialect) {
        this.sqlDialect = sqlDialect;
        return this;
    }

    public Lexer addTokenPattern(TokenPattern pattern) {
        patterns = ArrayUtils.add(patterns, pattern);
        return this;
    }

    public Lexer addTokenPatterns(TokenPattern... patterns) {
        for (TokenPattern pattern : patterns) {
            addTokenPattern(pattern);
        }
        return this;
    }

    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("--")) {
                continue;
            }
            tokens.addAll(tokenizeLine(trimmed));
        }
        return tokens;
    }

    private List<Token> tokenizeLine(String line) {
        List<Token> tokens = new ArrayList<>();
        int pos = 0;
        while (pos < line.length()) {
            TokenPatternMatch match = null;
            for (TokenPattern pattern : patterns) {
                if (pattern.getType().isDialectSupported(sqlDialect)) {
                    TokenPatternMatch pmatch = pattern.match(line, pos);
                    if (pmatch != null) {
                        if (match == null || pmatch.getMatchLength() > match.getMatchLength()) {
                            match = pmatch;
                        }
                    }
                }
            }
            if (match == null) {
                throw new LexerException("Unexpected character at position " + pos + " in line: " + line);
            }
            tokens.add(match.getToken());
            pos += match.getMatchLength();
        }
        return tokens;
    }
}

