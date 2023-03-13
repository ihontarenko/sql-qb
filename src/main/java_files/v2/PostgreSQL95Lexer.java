package pro.javadev.sql.v2;

public class PostgreSQL95Lexer extends SQLLexer {
    // додаткові регулярні вирази для PostgreSQL 9.5
    private static final String ARRAY_PATTERN = "\\bARRAY\\b";
    private static final String JSON_PATTERN = "\\bJSON\\b";
    private static final String UUID_PATTERN = "\\bUUID\\b";
    private static final String TOKEN_PATTERN = String.join("|", KEYWORDS_PATTERN, IDENTIFIER_PATTERN, NUMBER_PATTERN, OPERATOR_PATTERN, STRING_PATTERN, ARRAY_PATTERN, JSON_PATTERN, UUID_PATTERN);

    public PostgreSQL95Lexer(String input) {
        super(input);
        pattern = Pattern.compile(TOKEN_PATTERN);
        matcher = pattern.matcher(input);
    }

    // перевизначаємо метод nextToken() для підтримки додаткових лексем PostgreSQL 9.5
    @Override
    public Token nextToken() {
        if (matcher.find()) {
            String text = matcher.group();
            if (text.matches(KEYWORDS_PATTERN)) {
                return new KeywordToken(text);
            } else if (text.matches(IDENTIFIER_PATTERN)) {
                return new IdentifierToken(text);
            } else if (text.matches(NUMBER_PATTERN)) {
                return new NumberToken(text);
            } else if (text.matches(OPERATOR_PATTERN)) {
                return new OperatorToken(text);
            } else if (text.matches(STRING_PATTERN)) {
                return new StringToken(text);
            } else if (text.matches(ARRAY_PATTERN)) {
                return new ArrayToken(text);
            } else if (text.matches(JSON_PATTERN)) {
                return new JsonToken(text);
            } else if (text.matches(UUID_PATTERN)) {
                return new UuidToken(text);
            }
        }
        return null;
    }
