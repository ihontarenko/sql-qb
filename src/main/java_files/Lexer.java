package pro.javadev.sql;

public class Lexer import java.util.ArrayList;
        import java.util.List;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class Lexer {
    private final List<TokenPattern> patterns;
    private String input;
    private int position;

    public Lexer() {
        this.patterns = new ArrayList<>();
    }

    public void addTokenPattern(TokenType type, String regex) {
        patterns.add(new TokenPattern(type, Pattern.compile("^(" + regex + ")"), null));
    }

    public void addTokenPattern(TokenType type, String regex, String prefix) {
        patterns.add(new TokenPattern(type, Pattern.compile("^(" + regex + ")"), prefix));
    }

    public List<Token> tokenize(String input) {
        this.input = input;
        this.position = 0;
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            TokenPattern matchedPattern = null;
            int maxMatchLength = 0;

            for (TokenPattern pattern : patterns) {
                Matcher matcher = pattern.pattern.matcher(input.substring(position));
                if (matcher.find()) {
                    int matchLength = matcher.group().length();
                    if (matchLength > maxMatchLength) {
                        matchedPattern = pattern;
                        maxMatchLength = matchLength;
                    }
                }
            }

            if (matchedPattern != null) {
                String value = input.substring(position, position + maxMatchLength);
                if (matchedPattern.prefix != null) {
                    value = matchedPattern.prefix + value;
                }
                tokens.add(new Token(matchedPattern.type, value));
                position += maxMatchLength;
            } else {
                throw new IllegalArgumentException("Invalid input at position " + position);
            }
        }

        return tokens;
    }

    private static class TokenPattern {
        TokenType type;
        Pattern pattern;
        String prefix;

        TokenPattern(TokenType type, Pattern pattern, String prefix) {
            this.type = type;
            this.pattern = pattern;
            this.prefix = prefix;
        }
    }
}
{
}
