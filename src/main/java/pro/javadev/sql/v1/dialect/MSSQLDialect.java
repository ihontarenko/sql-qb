package pro.javadev.sql.v1.dialect;

import java.util.List;

public class MSSQLDialect implements Dialect {

    @Override
    public List<String> getKeywords() {
        return List.of("SELECT", "FROM", "WHERE", "GROUP BY", "HAVING", "ORDER BY", "ASC", "DESC", "UNION", "ALL", "MINUS", "INTERSECT", "JOIN", "INNER JOIN", "LEFT JOIN", "RIGHT JOIN", "FULL OUTER JOIN", "ON", "COUNT", "SUM", "MAX", "MIN", "AVG");
    }

    @Override
    public String getIdentifierQuoteString() {
        return "\"";
    }

    @Override
    public String getStringQuoteString() {
        return "'";
    }

    @Override
    public String getNumericLiteralQuoteString() {
        return "";
    }

    @Override
    public String getCommentStartString() {
        return "/*";
    }

    @Override
    public String getCommentEndString() {
        return "*/";
    }

    @Override
    public String escapeIdentifier(String identifier) {
        return "\"" + identifier.replaceAll("\"", "\"\"") + "\"";
    }

    @Override
    public String escapeString(String string) {
        return "'" + string.replaceAll("'", "''") + "'";
    }


}
