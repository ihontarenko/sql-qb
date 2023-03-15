package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.Context;
import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public interface ParserContext extends Context {

    <N extends ASTNode> void addParser(SQLDialect dialect, Class<? extends N> nodeClass, Parser<N> parser);

    <N extends ASTNode> Parser<N> getParser(SQLDialect dialect, Class<N> nodeClass);

    <N extends ASTNode> Map<Class<? extends N>, Map<SQLDialect, Parser<?>>> getParsers();

    class DefaultParserContext extends DefaultContext implements ParserContext {

        private static final String PARSER_PROPERTY = "PARSERS";

        public DefaultParserContext() {
            setProperty(PARSER_PROPERTY, new HashMap<>());
        }

        @Override
        public <N extends ASTNode> Map<Class<? extends N>, Map<SQLDialect, Parser<?>>> getParsers() {
            return getProperty(PARSER_PROPERTY);
        }

        @Override
        public <N extends ASTNode> void addParser(SQLDialect dialect, Class<? extends N> nodeClass, Parser<N> parser) {
            getParsers().computeIfAbsent(nodeClass, k -> new HashMap<>()).put(dialect, parser);
        }

        @Override
        public <N extends ASTNode> Parser<N> getParser(SQLDialect dialect, Class<N> nodeClass) {
            Parser<N> parser = (Parser<N>) getParsers().computeIfAbsent(nodeClass, k -> new HashMap<>()).get(dialect);

            if (parser != null) {
                return parser;
            }

            throw new RuntimeException("NO PARSER FOUND FOR NODE '" + nodeClass.getSimpleName() + "' AND DIALECT: " + dialect);
        }
    }

}
