package pro.javadev.sql.v1.dialect;

import java.util.List;

public interface Dialect {

    List<String> getKeywords();

    String getIdentifierQuoteString();

    String getStringQuoteString();

    String getNumericLiteralQuoteString();

    String getCommentStartString();

    String getCommentEndString();

    String escapeIdentifier(String identifier);

    String escapeString(String string);

}
