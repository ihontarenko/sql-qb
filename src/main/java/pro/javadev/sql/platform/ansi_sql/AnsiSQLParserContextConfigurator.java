package pro.javadev.sql.platform.ansi_sql;

import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.ast.statement.AliasExpression;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.ast.statement.SelectStatement;
import pro.javadev.sql.platform.ansi_sql.parser.statement.AliasExpressionParser;
import pro.javadev.sql.platform.ansi_sql.parser.IdentifierParser;
import pro.javadev.sql.platform.ansi_sql.parser.statement.SelectStatementParser;

import static pro.javadev.sql.library.SQLDialect.ANSI_SQL;

public class AnsiSQLParserContextConfigurator implements Configurator<ParserContext> {

    @Override
    public void configure(ParserContext ctx) {
        ctx.addParser(ANSI_SQL, IdentifierNode.class, new IdentifierParser());
        ctx.addParser(ANSI_SQL, AliasExpression.class, new AliasExpressionParser());
        ctx.addParser(ANSI_SQL, SelectStatement.class, new SelectStatementParser());

        ctx.setExpressionRecognizer(ANSI_SQL, new AnsiExpressionRecognizer());
    }

}
