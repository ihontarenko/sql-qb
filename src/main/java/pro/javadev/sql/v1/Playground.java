package pro.javadev.sql.v1;

import pro.javadev.sql.v1.dialect.SQLDialect;
import pro.javadev.sql.v1.lexer.Lexer;
import pro.javadev.sql.v1.lexer.LexerContext;
import pro.javadev.sql.v1.render.RendererContext;

public class Playground {

    private static String stringA = "UPDATE\n" +
            "Ignore Quick LOW_PRIORITY\n" +
            "    `users` U\n" +
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
            "     activities.starttime\n";

    private static String stringB = "select * from users";

    public static void main(String... arguments) {
        GlobalContext globalContext = new GlobalContext.DefaultGlobalContext();

        globalContext.setLexerContext(new LexerContext.DefaultLexerContext() {{
            setDialect(pro.javadev.sql.v1.dialect.SQLDialect.MYSQL);
        }});

        globalContext.setRendererContext(new RendererContext.DefaultRendererContext() {{
            setDialect(SQLDialect.MSSQL);
            registerRenderer(ASTNode.class, null);
        }});

        System.out.println(
                "FINISH"
        );
//
//        Lexer lexer = new Lexer(SQLDialect.ORACLE);
//
//        lexer.addMatcher(new TokenPattern("[a-zA-Z_]+[a-zA-Z0-9_]*", SQLToken.T_STRING));
//        lexer.addMatcher(new TokenPattern("SELECT", SQLToken.T_SQL_SELECT));

    }

}
