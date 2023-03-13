package pro.javadev.sql;

import pro.javadev.sql.dialect.Dialect;

public class LexerContext extends Context.DefaultContext {

    private static final String DIALECT_PROPERTY_NAME = "DIALECT_PROPERTY_NAME";

    public LexerContext() {

    }

    public Dialect getDialect() {
        return getProperty(DIALECT_PROPERTY_NAME);
    }

    public void setDialect(Dialect dialect) {
        setProperty(DIALECT_PROPERTY_NAME, dialect);
    }

}
