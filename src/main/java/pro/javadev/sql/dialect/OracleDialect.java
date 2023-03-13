package pro.javadev.sql.dialect;

import java.util.List;

public class OracleDialect implements Dialect {

    @Override
    public List<String> getKeywords() {
        // Перелік ключових слів Oracle
        return List.of("SELECT", "FROM", "WHERE", "GROUP BY", "HAVING", "ORDER BY", "ASC", "DESC", "CONNECT BY", "START WITH", "UNION", "ALL", "MINUS", "INTERSECT", "JOIN", "INNER JOIN", "LEFT JOIN", "RIGHT JOIN", "FULL OUTER JOIN", "ON", "NVL", "COUNT", "SUM", "MAX", "MIN", "AVG");
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