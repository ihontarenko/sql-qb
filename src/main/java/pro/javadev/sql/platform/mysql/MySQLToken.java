package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.internal.token.Token;

import java.util.regex.Pattern;

public enum MySQLToken implements Token {

    T_MYSQL_STRING(10000, null, "'([^'\\\\]*(\\\\.[^'\\\\]*)*)'|\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\""),
    T_MYSQL_MATCH(15000, "MATCH", "(?i)\\bMATCH\\b"),
    T_MYSQL_AGAINST(15100, "AGAINST", "(?i)\\bAGAINST\\b"),
    T_MYSQL_FIELD_PATH(13000, null, "(`?[a-zA-Z0-9_$]+`?+\\.(`?[a-zA-Z0-9_]+`?)*)"),
    T_MYSQL_QUOTED_IDENTIFIER(13500, null, "(`[a-zA-Z0-9_$]+`)");

    private final int     type;
    private final String  value;
    private final Pattern pattern;
    private final String  regexp;

    MySQLToken(int type, String value, String regexp) {
        this.type = type;
        this.value = value;
        this.pattern = Pattern.compile(regexp);
        this.regexp = regexp;
    }

    @Override
    public int type() {
        return type;
    }

    @Override
    public Pattern pattern() {
        return pattern;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String regexp() {
        return regexp;
    }
}
