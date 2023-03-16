package pro.javadev.sql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.lexer.TokenPattern;
import pro.javadev.sql.library.token.DefaultToken;
import pro.javadev.sql.platform.ansi_sql.AnsiSQLLexerContextConfigurator;
import pro.javadev.sql.platform.ansi_sql.ast.ColumnsNode;
import pro.javadev.sql.platform.mssql.MSSQLLexerContextConfigurator;
import pro.javadev.sql.platform.mysql.MySQLLexerContextConfigurator;
import pro.javadev.sql.platform.mysql.ast.LimitNode;
import pro.javadev.sql.platform.mysql.ast.OffsetNode;
import pro.javadev.sql.platform.ansi_sql.ast.SelectNode;
import pro.javadev.sql.library.lexer.Lexer;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.parser.SelectStatementParser;
import pro.javadev.sql.library.render.Renderer;
import pro.javadev.sql.library.render.RendererContext;
import pro.javadev.sql.library.render.SelectNodeRenderer;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.SQLLexer;
import pro.javadev.sql.platform.oracle.OracleLLexerContextConfigurator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Playground {

    public static void main(String... arguments) {
        LexerContext context = new LexerContext.DefaultLexerContext();
        Lexer        lexer   = new SQLLexer();

        context.setDialect(SQLDialect.MSSQL);

        new AnsiSQLLexerContextConfigurator().configure(context);
        new MySQLLexerContextConfigurator().configure(context);
        new MSSQLLexerContextConfigurator().configure(context);
        new OracleLLexerContextConfigurator().configure(context);

        Tokenizer tokenizer = lexer.tokenize(SQLDialect.ORACLE, context, getSQLString("select-oracle"));

        for (Token.Entry entry : tokenizer) {
            System.out.println(entry);
        }

/*        Lexer matchAgainst = new SQLLexer();
        LexerContext matchAgainstCtx = new LexerContext.DefaultLexerContext();

        matchAgainstCtx.addTokenPattern(SQLDialect.MYSQL, new TokenPattern(T_TILDA.regexp(), T_TILDA));
        matchAgainstCtx.addTokenPattern(SQLDialect.MYSQL, new TokenPattern(T_PLUS.regexp(), T_PLUS));
        matchAgainstCtx.addTokenPattern(SQLDialect.MYSQL, new TokenPattern(T_MINUS.regexp(), T_MINUS));
        matchAgainstCtx.addTokenPattern(SQLDialect.MYSQL, new TokenPattern(T_IDENTIFIER.regexp(), T_IDENTIFIER));

        for (Entry entry : lexer.tokenize(SQLDialect.MYSQL, matchAgainstCtx, "~string -exclusion +test +test2 -test4")) {
            System.out.println(entry);
        }*/

//        SelectNode node = new SelectNode();
//
//        node.add(new ColumnsNode("COLUMN_A"));
//        node.add(new ColumnsNode("COLUMN_B"));
//
//        node.add(new LimitNode(5));
//        node.add(new ColumnsNode("COLUMN_C"));
//        node.add(new OffsetNode(10));
//
//        System.out.println(
//                node.getColumns()
//        );
//
//        node.removeColumns();
//
//        node.add(new ColumnsNode("COLUMN_X"));
//
//        System.out.println(
//                "FINISH"
//        );
//
//        ParserContext parserContext = new ParserContext.DefaultParserContext();
//
//        parserContext.addParser(SQLDialect.ORACLE, SelectNode.class, new SelectStatementParser());
////        parserContext.addParser(SQLDialect.ORACLE, OffsetNode.class, new SelectStatementParser());
//        System.out.println("привіт");
//
//        RendererContext rendererContext = new RendererContext.DefaultRendererContext();
//
//        rendererContext.addRenderer(SQLDialect.MYSQL, SelectNode.class, new SelectNodeRenderer());
//
//        Renderer<SelectNode> renderer = rendererContext.getRenderer(SQLDialect.MYSQL, SelectNode.class);
//
//        System.out.println("renderer: " + renderer);
//        System.out.println("parser: " + parserContext.getParser(SQLDialect.MSSQL, SelectNode.class));
//
//        System.out.println(
//                new SelectNode().interpret(SQLDialect.MYSQL, rendererContext)
//        );


    }

    public static String getSQLString(String name) {
        String sql = null;

        try {
            sql = Files.readString(Paths.get(Objects.requireNonNull(Playground.class.getResource("/sql-examples/" + name + ".sql")).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return sql;
    }

}
