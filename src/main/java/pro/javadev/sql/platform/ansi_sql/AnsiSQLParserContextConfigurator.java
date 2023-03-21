package pro.javadev.sql.platform.ansi_sql;

import pro.javadev.sql.library.ast.*;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.platform.ansi_sql.parser.*;

import static pro.javadev.sql.library.SQLDialect.ANSI_SQL;

public class AnsiSQLParserContextConfigurator implements Configurator<ParserContext> {

    @Override
    public void configure(ParserContext ctx) {
        ctx.addParser(ANSI_SQL, FunctionCallExpression.class, new FunctionCallParser());
        ctx.addParser(ANSI_SQL, IdentifierNode.class, new IdentifierParser());
        ctx.addParser(ANSI_SQL, AliasExpression.class, new AliasExpressionParser());
        ctx.addParser(ANSI_SQL, SelectStatement.class, new SelectStatementParser());
        ctx.addParser(ANSI_SQL, ColumnItem.class, new ColumnItemParser());
        ctx.addParser(ANSI_SQL, FieldPathExpression.class, new FieldPathParser());
        ctx.addParser(ANSI_SQL, ExpressionItem.class, new ExpressionItemParser());

        ctx.setExpressionRecognizer(ANSI_SQL, new AnsiExpressionRecognizer());
    }

}
