package pro.javadev.sql.v2;

public class MySQL57Lexer extends SQLLexer {
    // додаткові регулярні вирази для MySQL 5.7
    private static final String DATE_PATTERN = "\\b([0-9]{4}-[0-9]{2}-[0-9]{2})\\b";
    private static final String TIME_PATTERN = "\\b([0-9]{2}:[0-9]{2}:[0-9]{2})\\b";
    private static final String DATETIME_PATTERN = "\\b([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2})\\b";
    private static final String TIMESTAMP_PATTERN = "\\b([0-9]{14})\\b";

    // об'єднуємо регулярні вирази для лексем
    private static final String TOKEN_PATTERN = String.join("|", KEYWORDS_PATTERN, IDENTIFIER_PATTERN, NUMBER_PATTERN, OPERATOR_PATTERN, STRING_PATTERN, DATE_PATTERN, TIME_PATTERN, DATETIME_PATTERN, TIMESTAMP_PATTERN);

    public MySQL57Lexer(String input) {
        super(input);
        pattern = Pattern.compile(TOKEN_PATTERN);
        matcher = pattern.matcher(input);
    }

    // перевизначаємо метод nextToken() для підтримки додаткових лексем MySQL 5.7
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
            } else if (text.matches(DATE_PATTERN)) {
                return new DateToken(text);
            } else if (text.matches(TIME_PATTERN)) {
                return new TimeToken(text);
            } else if (text.matches(DATETIME_PATTERN)) {
                return new DatetimeToken(text);
            } else if (text.matches(TIMESTAMP_PATTERN)) {
                return new TimestampToken(text);
            }
        }
        return null;
    }
}

public class PostgreSQL95Lexer extends SQLLexer {
    // додаткові регулярні вирази для PostgreSQL 9.5
    private static final String ARRAY_PATTERN = "\\bARRAY\\b";
    private static final String JSON_PATTERN = "\\bJSON\\b";
    private static final String UUID_PATTERN = "\\bUUID\\b";

// об'єднуємо регулярн

