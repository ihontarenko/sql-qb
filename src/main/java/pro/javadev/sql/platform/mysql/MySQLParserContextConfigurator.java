package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.internal.ast.FieldPathExpression;
import pro.javadev.sql.internal.ast.IdentifierNode;
import pro.javadev.sql.internal.common.Configurator;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.platform.mysql.parser.MySQLFieldPathParser;
import pro.javadev.sql.platform.mysql.parser.MySQLIdentifierParser;

import static pro.javadev.sql.internal.SQLDialect.MYSQL;

public class MySQLParserContextConfigurator implements Configurator<ParserContext> {

    @Override
    public void configure(ParserContext ctx) {
        ctx.addParser(MYSQL, FieldPathExpression.class, new MySQLFieldPathParser());
        ctx.addParser(MYSQL, IdentifierNode.class, new MySQLIdentifierParser());
        ctx.setExpressionRecognizer(MYSQL, new MySQLExpressionRecognizer());
    }
}
