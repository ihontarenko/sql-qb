package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.ast.FieldPathExpression;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.platform.mysql.parser.MySQLFieldPathParser;
import pro.javadev.sql.platform.mysql.parser.MySQLIdentifierParser;

import java.util.Map;

import static pro.javadev.sql.library.SQLDialect.ANSI_SQL;
import static pro.javadev.sql.library.SQLDialect.MYSQL;

public class MySQLParserContextConfigurator implements Configurator<ParserContext> {

    @Override
    public void configure(ParserContext ctx) {
        Map<Class<? extends ASTNode>, Parser<ASTNode>> parsers = ctx.getParsers().get(ANSI_SQL);

        parsers.forEach((nodeClass, parser)
                -> ctx.addParser(MYSQL, nodeClass, parser));

        ctx.addParser(MYSQL, FieldPathExpression.class, new MySQLFieldPathParser());
        ctx.addParser(MYSQL, IdentifierNode.class, new MySQLIdentifierParser());

        ctx.setExpressionRecognizer(MYSQL, new MySQLExpressionRecognizer());
    }
}
