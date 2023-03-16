package pro.javadev.sql.platform.ansi_sql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.library.lexer.TokenPattern;
import pro.javadev.sql.library.token.DefaultToken;

public class AnsiSQLLexerContextConfigurator implements Configurator<LexerContext> {

    @Override
    public void configure(LexerContext context) {
        SQLDialect dialect = SQLDialect.ANSI_SQL;

        for (DefaultToken value : DefaultToken.values()) {
            context.addTokenPattern(dialect, new TokenPattern(value.regexp(), value, dialect, value.type()));
        }

        for (SQLToken value : SQLToken.values()) {
            int priority = (100 - value.name().length()) + 20000;
            context.addTokenPattern(dialect, new TokenPattern(value.regexp(), value, dialect, priority));
        }
    }

}
