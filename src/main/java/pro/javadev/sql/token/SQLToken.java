package pro.javadev.sql.token;

import pro.javadev.sql.common.Token;

import java.util.regex.Pattern;

public enum SQLToken implements Token {

    T_SQL_AGAINST(10100, "AGAINST", "(?i)\\bAGAINST\\b"),
    T_SQL_ALL(10200, "ALL", "(?i)\\bALL\\b"),
    T_SQL_AND(10300, "AND", "(?i)\\bAND|\\&\\&"),
    T_SQL_OR(10330, "OR", "(?i)\\bOR|\\|\\|"),
    T_SQL_XOR(10360, "XOR", "(?i)\\bXOR|\\^"),
    T_SQL_AS(10400, "AS", "(?i)\\bAS\\b"),
    T_SQL_ASC(10500, "ASC", "(?i)\\bASC\\b"),
    T_SQL_BETWEEN(10600, "BETWEEN", "(?i)\\bBETWEEN\\b"),
    T_SQL_BY(10700, "BY", "(?i)\\bBY\\b"),
    T_SQL_DELETE(10800, "DELETE", "(?i)\\bDELETE\\b"),
    T_SQL_DESC(10900, "DESC", "(?i)\\bDESC\\b"),
    T_SQL_DISTINCT(11000, "DISTINCT", "(?i)\\bDISTINCT\\b"),
    T_SQL_EXPLAIN(11100, "EXPLAIN", "(?i)\\bEXPLAIN\\b"),
    T_SQL_FROM(11300, "FROM", "(?i)\\bFROM\\b"),
    T_SQL_GROUP(11400, "GROUP", "(?i)\\bGROUP\\b"),
    T_SQL_HAVING(11500, "HAVING", "(?i)\\bHAVING\\b"),
    T_SQL_IN(11600, "IN", "(?i)\\bIN\\b"),
    T_SQL_INNER(11700, "INNER", "(?i)\\bINNER\\b"),
    T_SQL_INSERT(11800, "INSERT", "(?i)\\bINSERT\\b"),
    T_SQL_INTO(11900, "INTO", "(?i)\\bINTO\\b"),
    T_SQL_IS(12000, "IS", "(?i)\\bIS\\b"),
    T_SQL_JOIN(12100, "JOIN", "(?i)\\bJOIN\\b"),
    T_SQL_LEFT(12200, "LEFT", "(?i)\\bLEFT\\b"),
    T_SQL_LIKE(12300, "LIKE", "(?i)\\bLIKE\\b"),
    T_SQL_LIMIT(12400, "LIMIT", "(?i)\\bLIMIT\\b"),
    T_SQL_MATCH(12500, "MATCH", "(?i)\\bMATCH\\b"),
    T_SQL_NOT(12600, "NOT", "(?i)\\bNOT\\b"),
    T_SQL_ON(12800, "ON", "(?i)\\bON\\b"),
    T_SQL_ORDER(13000, "ORDER", "(?i)\\bORDER\\b"),
    T_SQL_OUTER(13100, "OUTER", "(?i)\\bOUTER\\b"),
    T_SQL_RIGHT(13200, "RIGHT", "(?i)\\bRIGHT\\b"),
    T_SQL_SELECT(13300, "SELECT", "(?i)\\bSELECT\\b"),
    T_SQL_SET(13400, "SET", "(?i)\\bSET\\b"),
    T_SQL_STRAIGHT_JOIN(13500, "STRAIGHT_JOIN", "(?i)\\bSTRAIGHT_JOIN\\b"),
    T_SQL_TABLE(13600, "TABLE", "(?i)\\bTABLE\\b"),
    T_SQL_UPDATE(13800, "UPDATE", "(?i)\\bUPDATE\\b"),
    T_SQL_USING(13900, "USING", "(?i)\\bUSING\\b"),
    T_SQL_VALUES(14000, "VALUES", "(?i)\\bVALUES\\b"),
    T_SQL_WHERE(14100, "WHERE", "(?i)\\bWHERE\\b"),
    T_SQL_MODIFIER_LOW_PRIORITY(15000, "LOW_PRIORITY", "(?i)\\bLOW_PRIORITY\\b"),
    T_SQL_MODIFIER_HIGH_PRIORITY(15010, "HIGH_PRIORITY", "(?i)\\bHIGH_PRIORITY\\b"),
    T_SQL_MODIFIER_DELAYED(15020, "DELAYED", "(?i)\\bDELAYED\\b"),
    T_SQL_MODIFIER_IGNORE(15030, "IGNORE", "(?i)\\bIGNORE\\b"),
    T_SQL_MODIFIER_DISTINCTROW(15040, "DISTINCTROW", "(?i)\\bDISTINCTROW\\b"),
    T_SQL_MODIFIER_SQL_SMALL_RESULT(15050, "SQL_SMALL_RESULT", "(?i)\\bSQL_SMALL_RESULT\\b"),
    T_SQL_MODIFIER_SQL_BIG_RESULT(15060, "SQL_BIG_RESULT", "(?i)\\bSQL_BIG_RESULT\\b"),
    T_SQL_MODIFIER_SQL_BUFFER_RESULT(15070, "SQL_BUFFER_RESULT", "(?i)\\bSQL_BUFFER_RESULT\\b"),
    T_SQL_MODIFIER_SQL_CACHE(15080, "SQL_CACHE", "(?i)\\bSQL_CACHE\\b"),
    T_SQL_MODIFIER_SQL_NO_CACHE(15090, "SQL_NO_CACHE", "(?i)\\bSQL_NO_CACHE\\b"),
    T_SQL_MODIFIER_SQL_CALC_FOUND_ROWS(15100, "SQL_CALC_FOUND_ROWS", "(?i)\\bSQL_CALC_FOUND_ROWS\\b"),
    T_SQL_MODIFIER_QUICK(15110, "QUICK", "(?i)\\bQUICK\\b");

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
