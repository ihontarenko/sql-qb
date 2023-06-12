package pro.javadev.sql.platform.mssql;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.common.Configurator;
import pro.javadev.sql.internal.lexer.LexerContext;
import pro.javadev.sql.internal.lexer.TokenPattern;

public class MSSQLLexerContextConfigurator implements Configurator<LexerContext> {

    @Override
    public void configure(LexerContext context) {
        for (var value : MSSQLToken.values()) {
            context.addTokenPattern(SQLDialect.MSSQL, new TokenPattern(value.regexp(), value, SQLDialect.MSSQL, value.type()));
        }
    }

}
