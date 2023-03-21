package pro.javadev.sql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.node.Node;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.platform.ansi_sql.AnsiSQLLexerContextConfigurator;
import pro.javadev.sql.platform.ansi_sql.AnsiSQLParserContextConfigurator;
import pro.javadev.sql.library.ast.SelectStatement;
import pro.javadev.sql.platform.mssql.MSSQLLexerContextConfigurator;
import pro.javadev.sql.platform.mysql.MySQLLexerContextConfigurator;
import pro.javadev.sql.library.lexer.Lexer;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.SQLLexer;
import pro.javadev.sql.platform.mysql.MySQLParserContextConfigurator;
import pro.javadev.sql.platform.oracle.OracleLexerContextConfigurator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Objects;
import java.util.function.BiConsumer;

public class Playground {

    public static void main(String... arguments) {
        LexerContext context = new LexerContext.DefaultLexerContext();
        Lexer        lexer   = new SQLLexer();

        context.setDialect(SQLDialect.MSSQL);

        new AnsiSQLLexerContextConfigurator().configure(context);
        new MySQLLexerContextConfigurator().configure(context);
        new MSSQLLexerContextConfigurator().configure(context);
        new OracleLexerContextConfigurator().configure(context);

        Tokenizer tokenizer = lexer.tokenize(SQLDialect.MYSQL, context, getSQLString("select-mysql"));

        for (Token.Entry entry : lexer.tokenize(SQLDialect.ANSI_SQL, context, "select users.id from users")) {
            System.out.println(entry);
        }

        ParserContext parserContext = new ParserContext.DefaultParserContext();

        new AnsiSQLParserContextConfigurator().configure(parserContext);
        new MySQLParserContextConfigurator().configure(parserContext);

        Parser<SelectStatement> parser = parserContext.getParser(SQLDialect.MYSQL, SelectStatement.class);

        SelectStatement ast = parser.parse(SQLDialect.MYSQL, parserContext, tokenizer);

        for (Node node : ast.findAll(IdentifierNode.class)) {
            System.out.println(node);
        }

        System.out.println(
                renderXML(ast, 0)
        );

        BiConsumer<Node, Integer> consumer = (node, depth) -> {
            System.out.println("\t".repeat(depth) + node);
        };

        ast.execute(consumer);

    }

    public static String renderXML(ASTNode node, int depth) {
        StringBuilder builder = new StringBuilder();

        builder.append("\t".repeat(depth)).append("<%s>%n".formatted(node.name().toLowerCase(Locale.ROOT)));

        if (node.hasChildren()) {
            for (Node child : node.children()) {
                builder.append(renderXML((ASTNode) child, depth + 1));
            }
        } else {
            builder.append("\t".repeat(depth + 1)).append(node).append('\n');
        }

        builder.append("\t".repeat(depth)).append("</%s>%n".formatted(node.name().toLowerCase(Locale.ROOT)));

        return builder.toString();
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
