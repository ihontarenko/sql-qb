package pro.javadev.sql.dialect;

import java.util.List;

public enum SQLDialect implements Dialect {

    ANSI(new MySQLDialect()),
    MYSQL(new MySQLDialect()),
    ORACLE(new OracleDialect()),
    MSSQL(new MSSQLDialect());

    private final Dialect dialect;

    SQLDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    @Override
    public List<String> getKeywords() {
        return dialect.getKeywords();
    }

    @Override
    public String getIdentifierQuoteString() {
        return dialect.getIdentifierQuoteString();
    }

    @Override
    public String getStringQuoteString() {
        return dialect.getStringQuoteString();
    }

    @Override
    public String getNumericLiteralQuoteString() {
        return dialect.getNumericLiteralQuoteString();
    }

    @Override
    public String getCommentStartString() {
        return dialect.getCommentStartString();
    }

    @Override
    public String getCommentEndString() {
        return dialect.getCommentEndString();
    }

    @Override
    public String escapeIdentifier(String identifier) {
        return dialect.escapeIdentifier(identifier);
    }

    @Override
    public String escapeString(String string) {
        return dialect.escapeString(string);
    }

}
