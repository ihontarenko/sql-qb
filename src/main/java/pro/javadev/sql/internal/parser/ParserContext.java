package pro.javadev.sql.internal.parser;

import pro.javadev.sql.internal.Context;
import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public interface ParserContext extends Context {

    void setDialect(SQLDialect dialect);

    SQLDialect getDialect();

    <N extends ASTNode> void addParser(SQLDialect dialect, Class<? extends N> nodeClass, Parser<N> parser);

    <N extends ASTNode> Parser<N> getParser(SQLDialect dialect, Class<N> nodeClass);

    Map<SQLDialect, Map<Class<? extends ASTNode>, Parser<? extends ASTNode>>> getParsers();

    Map<Class<? extends ASTNode>, Parser<? extends ASTNode>> getParsers(SQLDialect dialect);

    void setExpressionRecognizer(SQLDialect dialect, ExpressionRecognizer recognizer);

    ExpressionRecognizer getExpressionRecognizer(SQLDialect dialect);

    class DefaultParserContext extends DefaultContext implements ParserContext {

        private static final String PARSER_PROPERTY      = "PARSERS";
        private static final String RECOGNIZERS_PROPERTY = "RECOGNIZERS";
        private static final String DIALECT_PROPERTY     = "DIALECT";

        public DefaultParserContext() {
            setProperty(PARSER_PROPERTY, new HashMap<>());
            setProperty(RECOGNIZERS_PROPERTY, new HashMap<>());
        }

        @Override
        public void setDialect(SQLDialect dialect) {
            setProperty(DIALECT_PROPERTY, dialect);
        }

        @Override
        public SQLDialect getDialect() {
            return getProperty(DIALECT_PROPERTY);
        }

        @Override
        public Map<SQLDialect, Map<Class<? extends ASTNode>, Parser<? extends ASTNode>>>  getParsers() {
            return getProperty(PARSER_PROPERTY);
        }

        @Override
        public Map<Class<? extends ASTNode>, Parser<? extends ASTNode>> getParsers(SQLDialect dialect) {
            return getParsers().computeIfAbsent(dialect, k -> new HashMap<>());
        }

        @Override
        public <N extends ASTNode> void addParser(SQLDialect dialect, Class<? extends N> nodeClass, Parser<N> parser) {
            getParsers(dialect).put(nodeClass, parser);
        }

        @Override
        public <N extends ASTNode> Parser<N> getParser(SQLDialect dialect, Class<N> nodeClass) {
            Parser<? extends ASTNode> parser = getParsers(dialect).get(nodeClass);

            if (parser == null) {
                parser = getParsers(getDialect()).get(nodeClass);
            }

            if (parser == null) {
                throw new ParserException("DIALECT: '%s' HAS NO PARSER FOR NODE TYPE '%s'"
                        .formatted(dialect, nodeClass.getSimpleName()));
            }

            return (Parser<N>) parser;
        }

        @Override
        public void setExpressionRecognizer(SQLDialect dialect, ExpressionRecognizer recognizer) {
            ((Map<SQLDialect, ExpressionRecognizer>)getProperty(RECOGNIZERS_PROPERTY)).put(dialect, recognizer);
        }

        @Override
        public ExpressionRecognizer getExpressionRecognizer(SQLDialect dialect) {
            return ((Map<SQLDialect, ExpressionRecognizer>)getProperty(RECOGNIZERS_PROPERTY)).get(dialect);
        }
    }

}
