package pro.javadev.sql.platform.ansi_sql;

import pro.javadev.sql.library.token.Token;

import java.util.regex.Pattern;

public enum SQLToken implements Token {

    T_SQL_ALL(90100, "ALL", "(?i)\\bALL\\b"),
    T_SQL_AND(90110, "AND", "(?i)\\bAND|\\&\\&"),
    T_SQL_OR(90120, "OR", "(?i)\\bOR|\\|\\|"),
    T_SQL_XOR(90130, "XOR", "(?i)\\bXOR|\\^"),
    T_SQL_AS(90200, "AS", "(?i)\\bAS\\b"),
    T_SQL_ASC(90500, "ASC", "(?i)\\bASC\\b"),
    T_SQL_BETWEEN(90600, "BETWEEN", "(?i)\\bBETWEEN\\b"),
    T_SQL_BY(90700, "BY", "(?i)\\bBY\\b"),
    T_SQL_DELETE(90800, "DELETE", "(?i)\\bDELETE\\b"),
    T_SQL_DESC(90900, "DESC", "(?i)\\bDESC\\b"),
    T_SQL_DISTINCT(91000, "DISTINCT", "(?i)\\bDISTINCT\\b"),
    T_SQL_EXPLAIN(91100, "EXPLAIN", "(?i)\\bEXPLAIN\\b"),
    T_SQL_FROM(91300, "FROM", "(?i)\\bFROM\\b"),
    T_SQL_GROUP(91400, "GROUP", "(?i)\\bGROUP\\b"),
    T_SQL_HAVING(91500, "HAVING", "(?i)\\bHAVING\\b"),
    T_SQL_IN(91600, "IN", "(?i)\\bIN\\b"),
    T_SQL_INNER(91700, "INNER", "(?i)\\bINNER\\b"),
    T_SQL_INSERT(91800, "INSERT", "(?i)\\bINSERT\\b"),
    T_SQL_INTO(91900, "INTO", "(?i)\\bINTO\\b"),
    T_SQL_IS(92000, "IS", "(?i)\\bIS\\b"),
    T_SQL_JOIN(92100, "JOIN", "(?i)\\bJOIN\\b"),
    T_SQL_LEFT(92200, "LEFT", "(?i)\\bLEFT\\b"),
    T_SQL_LIKE(92300, "LIKE", "(?i)\\bLIKE\\b"),
    T_SQL_LIMIT(92400, "LIMIT", "(?i)\\bLIMIT\\b"),
    T_SQL_NOT(92500, "NOT", "(?i)\\bNOT\\b"),
    T_SQL_ON(92600, "ON", "(?i)\\bON\\b"),
    T_SQL_ORDER(93000, "ORDER", "(?i)\\bORDER\\b"),
    T_SQL_OUTER(93100, "OUTER", "(?i)\\bOUTER\\b"),
    T_SQL_RIGHT(93200, "RIGHT", "(?i)\\bRIGHT\\b"),
    T_SQL_SELECT(93300, "SELECT", "(?i)\\bSELECT\\b"),
    T_SQL_SET(93400, "SET", "(?i)\\bSET\\b"),
    T_SQL_STRAIGHT_JOIN(93500, "STRAIGHT_JOIN", "(?i)\\bSTRAIGHT_JOIN\\b"),
    T_SQL_TABLE(93600, "TABLE", "(?i)\\bTABLE\\b"),
    T_SQL_UPDATE(93800, "UPDATE", "(?i)\\bUPDATE\\b"),
    T_SQL_USING(93900, "USING", "(?i)\\bUSING\\b"),
    T_SQL_VALUES(94000, "VALUES", "(?i)\\bVALUES\\b"),
    T_SQL_WHERE(94100, "WHERE", "(?i)\\bWHERE\\b"),
    T_SQL_MODIFIER_LOW_PRIORITY(95000, "LOW_PRIORITY", "(?i)\\bLOW_PRIORITY\\b"),
    T_SQL_MODIFIER_HIGH_PRIORITY(95010, "HIGH_PRIORITY", "(?i)\\bHIGH_PRIORITY\\b"),
    T_SQL_MODIFIER_DELAYED(95020, "DELAYED", "(?i)\\bDELAYED\\b"),
    T_SQL_MODIFIER_IGNORE(95030, "IGNORE", "(?i)\\bIGNORE\\b"),
    T_SQL_MODIFIER_DISTINCTROW(95040, "DISTINCTROW", "(?i)\\bDISTINCTROW\\b"),
    T_SQL_MODIFIER_SQL_SMALL_RESULT(95050, "SQL_SMALL_RESULT", "(?i)\\bSQL_SMALL_RESULT\\b"),
    T_SQL_MODIFIER_SQL_BIG_RESULT(95060, "SQL_BIG_RESULT", "(?i)\\bSQL_BIG_RESULT\\b"),
    T_SQL_MODIFIER_SQL_BUFFER_RESULT(95070, "SQL_BUFFER_RESULT", "(?i)\\bSQL_BUFFER_RESULT\\b"),
    T_SQL_MODIFIER_SQL_CACHE(95080, "SQL_CACHE", "(?i)\\bSQL_CACHE\\b"),
    T_SQL_MODIFIER_SQL_NO_CACHE(95090, "SQL_NO_CACHE", "(?i)\\bSQL_NO_CACHE\\b"),
    T_SQL_MODIFIER_SQL_CALC_FOUND_ROWS(95100, "SQL_CALC_FOUND_ROWS", "(?i)\\bSQL_CALC_FOUND_ROWS\\b"),
    T_SQL_MODIFIER_QUICK(95110, "QUICK", "(?i)\\bQUICK\\b");

    private final int     type;
    private final Pattern pattern;
    private final String  value;
    private final String  regexp;

    SQLToken(final int type, final String value, final String regexp) {
        this.type = type;
        this.pattern = Pattern.compile(regexp);
        this.value = value;
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
