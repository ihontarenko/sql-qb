package pro.javadev.sql.lexer1;

public class SQLLexer {

    private final List<TokenPattern> patterns;
    private final String input;

    public SQLLexer(SQLDialect dialect, String input) {
        this.patterns = TokenPatternFactory.createTokenPatterns(dialect);
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        int pos = 0;
        while (pos < input.length()) {
            Optional<TokenMatch> match = patterns.stream()
                    .map(p -> p.find(input, pos))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();

            if (match.isPresent()) {
                TokenMatch tokenMatch = match.get();
                tokens.add(new Token(tokenMatch.getType(), tokenMatch.getValue()));
                pos = tokenMatch.getEnd();
            } else {
                throw new LexerException("Invalid character at position " + pos);
            }
        }
        return tokens;
    }
}
