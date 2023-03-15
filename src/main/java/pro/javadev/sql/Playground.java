package pro.javadev.sql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ColumnsNode;
import pro.javadev.sql.library.ast.LimitNode;
import pro.javadev.sql.library.ast.OffsetNode;
import pro.javadev.sql.library.ast.SelectNode;
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

        Tokenizer tokenizer = lexer.tokenize(SQLDialect.ORACLE, context, getSQLString("delete"));

        for (Token.Entry entry : tokenizer) {
            System.out.println(entry);
        }

        SelectNode node = new SelectNode();

        node.add(new ColumnsNode("COLUMN_A"));
        node.add(new ColumnsNode("COLUMN_B"));

        node.add(new LimitNode(5));
        node.add(new ColumnsNode("COLUMN_C"));
        node.add(new OffsetNode(10));

        System.out.println(
                node.getColumns()
        );

        node.removeColumns();

        node.add(new ColumnsNode("COLUMN_X"));

        System.out.println(
                "FINISH"
        );

        ParserContext parserContext = new ParserContext.DefaultParserContext();

        parserContext.addParser(SQLDialect.ORACLE, SelectNode.class, new SelectStatementParser());
//        parserContext.addParser(SQLDialect.ORACLE, OffsetNode.class, new SelectStatementParser());


        RendererContext rendererContext = new RendererContext.DefaultRendererContext();

        rendererContext.addRenderer(SQLDialect.SQLITE, SelectNode.class, new SelectNodeRenderer());

        Renderer<SelectNode> renderer = rendererContext.getRenderer(SQLDialect.SQLITE, SelectNode.class);

        System.out.println("renderer: " + renderer);
        System.out.println("parser: " + parserContext.getParser(SQLDialect.ORACLE, SelectNode.class));

        System.out.println(
                new SelectNode().interpret(SQLDialect.SQLITE, rendererContext)
        );
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
