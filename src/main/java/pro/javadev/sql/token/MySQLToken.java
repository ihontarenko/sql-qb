package pro.javadev.sql.token;

import pro.javadev.sql.common.Token;

import java.util.regex.Pattern;

public enum MySQLToken implements Token {

    T_MYSQL_QUOTED_IDENTIFIER(90000, null, "`[a-zA-Z_][a-zA-Z0-9_]*`");

    private final int type;
    private final String value;
    private final Pattern pattern;
    private final String regexp;

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
