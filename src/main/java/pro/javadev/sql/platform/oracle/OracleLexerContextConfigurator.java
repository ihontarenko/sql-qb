package pro.javadev.sql.platform.oracle;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.common.Configurator;
import pro.javadev.sql.internal.lexer.LexerContext;
import pro.javadev.sql.internal.lexer.TokenPattern;

public class OracleLexerContextConfigurator implements Configurator<LexerContext> {

    @Override
    public void configure(LexerContext context) {
        for (var value : OracleToken.values()) {
            context.addTokenPattern(SQLDialect.ORACLE, new TokenPattern(value.regexp(), value, SQLDialect.ORACLE, value.type()));
        }
    }

}
