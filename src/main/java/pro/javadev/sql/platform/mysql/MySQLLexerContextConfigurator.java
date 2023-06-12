package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.common.Configurator;
import pro.javadev.sql.internal.lexer.LexerContext;
import pro.javadev.sql.internal.lexer.TokenPattern;

public class MySQLLexerContextConfigurator implements Configurator<LexerContext> {

    @Override
    public void configure(LexerContext context) {
        for (var value : MySQLToken.values()) {
            context.addTokenPattern(SQLDialect.MYSQL, new TokenPattern(value.regexp(), value, SQLDialect.MYSQL, value.type()));
        }
    }

}
