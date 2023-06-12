package pro.javadev.sql.internal.token;

import java.util.regex.Pattern;

public enum DefaultToken implements Token {

    T_MATH_EQ(1001010, "=", "="),
    T_LOGICAL_EQ(1001010, "==", "=="),
    T_LOGICAL_NE(1001020, "!=", "!="),
    T_GE(1001030, ">=", ">="),
    T_GT(1001040, ">", ">"),
    T_LE(1001050, "<=", "<="),
    T_LT(1001060, "<", "<"),
    T_AT(1002000, "@", "@"),
    T_HASH(1002010, "#", "#"),
    T_MINUS(1002020, "-", "-"),
    T_PLUS(1002030, "+", "\\+"),
    T_DIVIDE(1002040, "/", "/"),
    T_MULTIPLY(1002050, "*", "\\*"),
    T_DOT(1002060, ".", "\\."),
    T_COMMA(1002070, ",", ","),
    T_NEGATE(1002080, "!", "!"),
    T_QUESTION(1002090, "?", "\\?"),
    T_BACKSLASH(1002100, "\\/", "\\/"),
    T_VERTICAL_SLASH(1002110, "|", "\\|"),
    T_AMPERSAND(1002120, "&", "&"),
    T_COLON(1002130, ":", ":"),
    T_SEMICOLON(1002140, ";", ";"),
    T_GRAVE_ACCENT(1002150, "`", "`"),
    T_OPEN_BRACE(1002160, "(", "\\("),
    T_OPEN_CURLY_BRACE(1002170, "{", "\\{"),
    T_CLOSE_BRACE(1002180, ")", "\\)"),
    T_CLOSE_CURLY_BRACE(1002190, "}", "\\}"),
    T_TILDA(1002200, "~", "~"),
    T_PERCENT(1002210, "%", "%"),
    T_CARET(1002220, "^", "\\^"),
    T_DOLLAR(1002220, "$", "\\$"),
    T_OPEN_BRACKET(1002230, "[", "\\["),
    T_CLOSE_BRACKET(1002240, "]", "\\]"),
    T_STRING(1003000, null, "'([^'\\\\]*(\\\\.[^'\\\\]*)*)'|\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\""),
    T_INT(1004000, null, "-?[0-9]+"),
    T_FLOAT(1005000, null, "-?[0-9]+\\.?[0-9]*"),
    T_FALSE(1009000, "FALSE", "false"),
    T_TRUE(1009001, "TRUE", "true"),
    T_NULL(1009999, "NULL", "null"),
    T_IDENTIFIER(1007000, null, "[a-zA-Z_][a-zA-Z0-9_]*"),
    T_FIELD_PATH(1006000, null, "[a-zA-Z0-9_$]+\\.[a-zA-Z0-9_]*");

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
