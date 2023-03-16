package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.common.Configurator;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.library.lexer.TokenPattern;
import pro.javadev.sql.library.token.DefaultToken;
import pro.javadev.sql.platform.ansi_sql.SQLToken;

public class MySQLLexerContextConfigurator implements Configurator<LexerContext> {

    @Override
    public void configure(LexerContext context) {
        for (var value : MySQLToken.values()) {
            context.addTokenPattern(SQLDialect.MYSQL, new TokenPattern(value.regexp(), value, SQLDialect.MYSQL, value.type()));
        }
    }

}
