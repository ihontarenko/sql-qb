package pro.javadev.sql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.*;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.library.lexer.Lexer;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.platform.SQLLexer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Playground {

    public static void main(String... arguments) {
        LexerContext context = new LexerContext.DefaultLexerContext();
        Lexer lexer = new SQLLexer();

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
