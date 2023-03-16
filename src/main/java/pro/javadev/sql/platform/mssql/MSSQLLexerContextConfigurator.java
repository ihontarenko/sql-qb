package pro.javadev.sql.platform.mssql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.library.lexer.TokenPattern;

public class MSSQLLexerContextConfigurator implements Configurator<LexerContext> {

    @Override
    public void configure(LexerContext context) {
        for (var value : MSSQLToken.values()) {
            context.addTokenPattern(SQLDialect.MSSQL, new TokenPattern(value.regexp(), value, SQLDialect.MSSQL, value.type()));
        }
    }

}
