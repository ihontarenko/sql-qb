package pro.javadev.sql.library.lexer;

import pro.javadev.sql.library.Context;
import pro.javadev.sql.library.SQLDialect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LexerContext extends Context {

    SQLDialect getDialect();

    void setDialect(SQLDialect dialect);

    List<TokenPattern> getTokenPatterns(SQLDialect dialect);

    void addTokenPattern(SQLDialect dialect, TokenPattern matcher);

    class DefaultLexerContext extends Context.DefaultContext implements LexerContext {

        private static final String DIALECT_PROPERTY_NAME = "DIALECT_PROPERTY_NAME";
        private static final String MATCHER_PROPERTY_NAME = "MATCHER_PROPERTY_NAME";

        public DefaultLexerContext() {
            setProperty(MATCHER_PROPERTY_NAME, new HashMap<>());
        }

        @Override
        public List<TokenPattern> getTokenPatterns(SQLDialect dialect) {
            Map<SQLDialect, List<TokenPattern>> matchers = getProperty(MATCHER_PROPERTY_NAME);
            return matchers.computeIfAbsent(dialect, d -> new ArrayList<>());
        }

        @Override
        public void addTokenPattern(SQLDialect dialect, TokenPattern matcher) {
            getTokenPatterns(dialect).add(matcher);
        }

        @Override
        public SQLDialect getDialect() {
            return getProperty(DIALECT_PROPERTY_NAME);
        }

        @Override
        public void setDialect(SQLDialect dialect) {
            setProperty(DIALECT_PROPERTY_NAME, dialect);
        }

    }

}
