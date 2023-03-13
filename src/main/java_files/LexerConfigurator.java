package pro.javadev.sql;

public class LexerConfigurator {

    private final SQLDialect dialect;
    private final Lexer lexer;

    public LexerConfigurator(SQLDialect dialect) {
        this.dialect = dialect;
        this.lexer = new Lexer(dialect);

        switch (dialect) {
            case MYSQL:
                configureMySQLLexer();
                break;
            case MSSQL:
                configureMSSQLLexer();
                break;
            case ORACLE:
                configureOracleLexer();
                break;
            // add configurations for other dialects here
            default:
                throw new UnsupportedOperationException("SQL dialect " + dialect + " is not supported");
        }
    }

    private void configureMySQLLexer() {
        // add token patterns specific to MySQL dialect
        lexer.addTokenPattern(TokenType.KEYWORD, "UNSIGNED|ZEROFILL", "\\b");
        // configure other MySQL-specific options
    }

    private void configureMSSQLLexer() {
        // add token patterns specific to MSSQL dialect
        lexer.addTokenPattern(TokenType.KEYWORD, "TOP|OFFSET|FETCH", "\\b");
        // configure other MSSQL-specific options
    }

    private void configureOracleLexer() {
        // add token patterns specific to Oracle dialect
        lexer.addTokenPattern(TokenType.KEYWORD, "CONNECT BY|NOCYCLE|PRIOR|ROWNUM", "\\b");
        // configure other Oracle-specific options
    }

    public Lexer getLexer() {
        return lexer;
    }
}
