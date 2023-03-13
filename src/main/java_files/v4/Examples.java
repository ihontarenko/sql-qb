package pro.javadev.sql.v4;

public class Examples {

    public interface SQLLexer {
        SQLToken getNextToken();
    }

    public interface SQLTokenizer {
        List<SQLToken> tokenize(String input);
    }

    public class SQLPatternLexer implements SQLLexer {
        private final String input;
        private int position;
        private SQLPattern currentPattern;
        private SQLToken currentToken;

        public SQLPatternLexer(String input, SQLPattern pattern) {
            this.input = input;
            position = 0;
            currentPattern = pattern;
            currentToken = null;
        }

        @Override
        public SQLToken getNextToken() {
            if (position >= input.length()) {
                return null;
            }

            skipWhiteSpace();

            for (Map.Entry<SQLTokenType, Pattern> entry : currentPattern.getPatterns().entrySet()) {
                Matcher matcher = entry.getValue().matcher(input.substring(position));
                if (matcher.lookingAt()) {
                    String text = matcher.group();
                    currentToken = new SQLToken(entry.getKey(), text);
                    position += text.length();
                    return currentToken;
                }
            }

            throw new IllegalArgumentException("Invalid character: " + input.charAt(position));
        }

        private void skipWhiteSpace() {
            while (position < input.length() && Character.isWhitespace(input.charAt(position))) {
                position++;
            }
        }
    }

    public class SQLPatternTokenizer implements SQLTokenizer {
        private final SQLPattern pattern;

        public SQLPatternTokenizer(SQLPattern pattern) {
            this.pattern = pattern;
        }

        @Override
        public List<SQLToken> tokenize(String input) {
            List<SQLToken> tokens = new ArrayList<>();
            SQLLexer lexer = new SQLPatternLexer(input, pattern);
            SQLToken token = lexer.getNextToken();
            while (token != null) {
                tokens.add(token);
                token = lexer.getNextToken();
            }
            return tokens;
        }
    }

    public class SQLPattern {
        private final Map<SQLTokenType, Pattern> patterns;

        public SQLPattern(Map<SQLTokenType, Pattern> patterns) {
            this.patterns = patterns;
        }

        public Map<SQLTokenType, Pattern> getPatterns() {
            return patterns;
        }
    }

    public enum SQLDialect {
        MYSQL57,
        POSTGRESQL95
    }

    public class SQLDialectFactory {
        public static SQLPattern createPattern(SQLDialect dialect) {
            switch (dialect) {
                case MYSQL57:
                    return new SQLPattern(Map.of(
                            SQLTokenType.LEFT_PAREN, Pattern.compile("\\("),
                            SQLTokenType.RIGHT_PAREN, Pattern.compile("\\)"),
                            SQLTokenType.COMMA, Pattern.compile(","),
                            SQLTokenType.PLUS, Pattern.compile("\\+"),
                            SQLTokenType.MINUS, Pattern.compile("-"),
                            SQLTokenType.MULTIPLY, Pattern.compile("\\*"),
                            SQLTokenType.DIVIDE, Pattern.compile("/"),
                            SQLTokenType.KEY
                            WORD, Pattern.compile("\b\w+\b"),
                            SQLTokenType.SELECT, Pattern.compile("\bSELECT\b"),
                            SQLTokenType.FROM, Pattern.compile("\bFROM\b"),
                            SQLTokenType.WHERE, Pattern.compile("\bWHERE\b"),
                            SQLTokenType.ORDER, Pattern.compile("\bORDER\b"),
                            SQLTokenType.BY, Pattern.compile("\bBY\b"),
                            SQLTokenType.ASC, Pattern.compile("\bASC\b"),
                            SQLTokenType.DESC, Pattern.compile("\bDESC\b"),
                            SQLTokenType.EQUALS, Pattern.compile("="),
                            SQLTokenType.NUMBER, Pattern.compile("\d+")
                    ));
                case POSTGRESQL95:
                    return new SQLPattern(Map.of(
                            SQLTokenType.LEFT_PAREN, Pattern.compile("\("),
                            SQLTokenType.RIGHT_PAREN, Pattern.compile("\)"),
                            SQLTokenType.COMMA, Pattern.compile(","),
                            SQLTokenType.PLUS, Pattern.compile("\+"),
                            SQLTokenType.MINUS, Pattern.compile("-"),
                            SQLTokenType.MULTIPLY, Pattern.compile("\*"),
                            SQLTokenType.DIVIDE, Pattern.compile("/"),
                            SQLTokenType.KEYWORD, Pattern.compile("\b\w+\b"),
                            SQLTokenType.SELECT, Pattern.compile("\bSELECT\b"),
                            SQLTokenType.FROM, Pattern.compile("\bFROM\b"),
                            SQLTokenType.WHERE, Pattern.compile("\bWHERE\b"),
                            SQLTokenType.ORDER, Pattern.compile("\bORDER\b"),
                            SQLTokenType.BY, Pattern.compile("\bBY\b"),
                            SQLTokenType.ASC, Pattern.compile("\bASC\b"),
                            SQLTokenType.DESC, Pattern.compile("\bDESC\b"),
                            SQLTokenType.EQUALS, Pattern.compile("="),
                            SQLTokenType.NUMBER, Pattern.compile("\d+")
                    ));
                default:
                    throw new IllegalArgumentException("Unsupported SQL dialect: " + dialect);
            }
        }
    }

            }
