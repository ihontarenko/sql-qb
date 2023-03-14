package pro.javadev.sql;

import pro.javadev.sql.common.Token;
import pro.javadev.sql.common.Tokenizer;
import pro.javadev.sql.lexer.Lexer;
import pro.javadev.sql.lexer.LexerContext;
import pro.javadev.sql.lexer.SQLLexer;

import java.util.List;

public class Playground {

    private static String stringA = "UPDATE\n" +
            "IGNORE Quick LOW_PRIORITY\n" +
            "    `users` U \"sas\" as a " +
            "SET\n" +
            "`country`='Ukraine', U.city='Kyiv',\n" +
            " u.id=MAX(Distinct u.id) + 1\n" +
            "WHERE a > 1 && (b > 3) || a in(1, 2,3 ) && z != Min(U.id)\n" +
            "anD\n" +
            "  u.id < 100 and u.create >= 1000000\n" +
            "  &&\n" +
            "  u.login_cnt > (10 + 20 * sum(u.test) / 2 + 1)\n" +
            "  and\n" +
            "  u.name > 3 ||\n" +
            "(gateway not like '%pay-gw%' and u.name like 'test')\n" +
            "    AND (\n" +
            "      ( activities.orderby = 1\n" +
            "    AND activities.starttime >= '2013-08-26 04:00:00'\n" +
            "    AND activities.endtime <= '2013-08-27 04:00:00'\n" +
            "      )\n" +
            "    or ( activities.orderby != 1 AND activities.activitydate = '2013-08-26')\n" +
            "  )\n" +
            "&& 1 != 1 || 2 >= 1\n" +
            "ORDER  BY activitytypes.orderby,\n" +
            "     activities.starttime and count(*)\n";

    private static String stringB = "select * from users";

    public static void main(String... arguments) {
        LexerContext context = new LexerContext.DefaultLexerContext();
        Lexer lexer = new SQLLexer();

        context.setDialect(SQLDialect.MSSQL);

        Tokenizer tokenizer = lexer.tokenize(SQLDialect.ORACLE, context, stringA);

        for (Token.Entry entry : tokenizer) {
            System.out.println(entry);
        }

        tokenizer.add(null);
    }

}
