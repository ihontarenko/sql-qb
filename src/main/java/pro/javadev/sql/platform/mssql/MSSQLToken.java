package pro.javadev.sql.platform.mssql;

import pro.javadev.sql.library.token.Token;

import java.util.regex.Pattern;

public enum MSSQLToken implements Token {

    T_MSSQL_STRING(10000, null, "'([^']|'')*'"),
    T_MSSQL_FIELD_PATH(13000, null, "(\\[?[a-zA-Z0-9_$]+\\]?+\\.(\\[?[a-zA-Z0-9_]+\\]?)*)"),
    T_MSSQL_QUOTED_IDENTIFIER(13500, null, "(\\[[a-zA-Z0-9_$]+\\])"),
    ;

    private final int     type;
    private final String  value;
    private final Pattern pattern;
    private final String  regexp;

    MSSQLToken(int type, String value, String regexp) {
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
