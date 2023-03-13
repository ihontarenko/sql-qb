package pro.javadev.sql;

public class PostgreSQLTokenizerConfigurator implements TokenizerConfigurator {

    @Override
    public void configure(Tokenizer tokenizer) {
        tokenizer.addTokenPattern(TokenType.KEYWORD, "SELECT", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "FROM", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "WHERE", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "GROUP BY", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "HAVING", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "ORDER BY", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "LIMIT", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "OFFSET", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "AS", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "JOIN", "(?i)");
        tokenizer.addTokenPattern(TokenType.KEYWORD, "ON", "(?i)");

        tokenizer.addTokenPattern(TokenType.OPERATOR, "\\*", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, "\\.", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, "\\(", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, "\\)", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, ",", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, "=", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, "!=", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, "<>", "");
        tokenizer.addTokenPattern(TokenType.OPERATOR, ">", "");
        tokenizer.addTokenPattern(TokenType
