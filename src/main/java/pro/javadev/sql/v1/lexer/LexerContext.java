package pro.javadev.sql.v1.lexer;

import pro.javadev.sql.v1.Context;
import pro.javadev.sql.v1.dialect.Dialect;

public interface LexerContext extends Context {

    Dialect getDialect();

    void setDialect(Dialect dialect);

    class DefaultLexerContext extends Context.DefaultContext implements LexerContext {

        private static final String DIALECT_PROPERTY_NAME = "DIALECT_PROPERTY_NAME";

        public DefaultLexerContext() {

        }

        @Override
        public Dialect getDialect() {
            return getProperty(DIALECT_PROPERTY_NAME);
        }

        @Override
        public void setDialect(Dialect dialect) {
            setProperty(DIALECT_PROPERTY_NAME, dialect);
        }

    }

}
