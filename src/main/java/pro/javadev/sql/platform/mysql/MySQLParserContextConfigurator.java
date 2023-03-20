package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.ast.statement.ColumnItem;
import pro.javadev.sql.platform.mysql.parser.MySQLColumnItemParser;

import java.util.Map;

import static pro.javadev.sql.library.SQLDialect.ANSI_SQL;
import static pro.javadev.sql.library.SQLDialect.MYSQL;

public class MySQLParserContextConfigurator implements Configurator<ParserContext> {

    @Override
    public void configure(ParserContext ctx) {
        Map<Class<? extends ASTNode>, Parser<ASTNode>> parsers = ctx.getParsers().get(ANSI_SQL);

        parsers.forEach((nodeClass, parser)
                -> ctx.addParser(MYSQL, nodeClass, parser));

        ctx.addParser(MYSQL, ColumnItem.class, new MySQLColumnItemParser());

        ctx.setExpressionRecognizer(MYSQL, new MySQLExpressionRecognizer());
    }
}
