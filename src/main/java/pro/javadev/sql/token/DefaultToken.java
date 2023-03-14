package pro.javadev.sql.token;

import pro.javadev.sql.common.Token;

import java.util.regex.Pattern;

public enum DefaultToken implements Token {

    T_MATH_EQ(1010, "=", "="),
    T_LOGICAL_EQ(1010, "==", "=="),
    T_LOGICAL_NE(1020, "!=", "!="),
    T_GT(1030, ">", ">"),
    T_GE(1040, ">=", ">="),
    T_LT(1050, "<", "<"),
    T_LE(1060, "<=", "<="),
    T_AT(2000, "@", "@"),
    T_HASH(2010, "#", "#"),
    T_MINUS(2020, "-", "-"),
    T_PLUS(2030, "+", "\\+"),
    T_DIVIDE(2040, "/", "/"),
    T_MULTIPLY(2050, "*", "\\*"),
    T_DOT(2060, ".", "\\."),
    T_COMMA(2070, ",", ","),
    T_NEGATE(2080, "!", "!"),
    T_QUESTION(2090, "?", "\\?"),
    T_BACKSLASH(2100, "\\/", "\\/"),
    T_VERTICAL_SLASH(2110, "|", "\\|"),
    T_AMPERSAND(2120, "&", "&"),
    T_COLON(2130, ":", ":"),
    T_SEMICOLON(2140, ";", ";"),
    T_GRAVE_ACCENT(2150, "`", "`"),
    T_OPEN_BRACE(2160, "(", "\\("),
    T_OPEN_CURLY_BRACE(2170, "{", "\\{"),
    T_CLOSE_BRACE(2180, ")", "\\)"),
    T_CLOSE_CURLY_BRACE(2190, "}", "\\}"),
    T_TILDA(2200, "~", "~"),
    T_PERCENT(2210, "%", "%"),
    T_CARET(2220, "^", "\\^"),
    T_DOLLAR(2220, "$", "\\$"),
    T_OPEN_BRACKET(2230, "[", "\\["),
    T_CLOSE_BRACKET(2240, "]", "\\]"),
    T_STRING(3000, null, "'([^'\\\\]*(\\\\.[^'\\\\]*)*)'|\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\""),
    T_INT(4000, null, "-?[0-9]+"),
    T_FLOAT(5000, null, "-?[0-9]+\\.?[0-9]*"),
    T_FALSE(9000, "FALSE", "false"),
    T_TRUE(9001, "TRUE", "true"),
    T_NULL(9999, "NULL", "null"),
    T_IDENTIFIER(7000, null, "[a-zA-Z_][a-zA-Z0-9_]*");

    private final int     type;
    private final String  value;
    private final Pattern pattern;
    private final String  regexp;

    DefaultToken(final int type, final String value, final String regexp) {
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
