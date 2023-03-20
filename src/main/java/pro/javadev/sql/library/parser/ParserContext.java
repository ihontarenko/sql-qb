package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.Context;
import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public interface ParserContext extends Context {

    <N extends ASTNode> void addParser(SQLDialect dialect, Class<? extends N> nodeClass, Parser<N> parser);

    <N extends ASTNode> Parser<N> getParser(SQLDialect dialect, Class<N> nodeClass);

    <N extends ASTNode> Map<SQLDialect, Map<Class<? extends N>, Parser<N>>> getParsers();

    void setExpressionRecognizer(SQLDialect dialect, ExpressionRecognizer recognizer);

    ExpressionRecognizer getExpressionRecognizer(SQLDialect dialect);

    class DefaultParserContext extends DefaultContext implements ParserContext {

        private static final String PARSER_PROPERTY      = "PARSERS";
        private static final String RECOGNIZERS_PROPERTY = "RECOGNIZERS";

        public DefaultParserContext() {
            setProperty(PARSER_PROPERTY, new HashMap<>());
            setProperty(RECOGNIZERS_PROPERTY, new HashMap<>());
        }

        @Override
        public <N extends ASTNode> Map<SQLDialect, Map<Class<? extends N>, Parser<N>>> getParsers() {
            return getProperty(PARSER_PROPERTY);
        }

        @Override
        public <N extends ASTNode> void addParser(SQLDialect dialect, Class<? extends N> nodeClass, Parser<N> parser) {
            getParsers().computeIfAbsent(dialect, k -> new HashMap<>()).put(nodeClass, (Parser<ASTNode>) parser);
        }

        @Override
        public <N extends ASTNode> Parser<N> getParser(SQLDialect dialect, Class<N> nodeClass) {
            Parser<N> parser = (Parser<N>) getParsers().computeIfAbsent(dialect, k -> new HashMap<>()).get(nodeClass);

            if (parser != null) {
                return parser;
            }

            throw new ParserException("DIALECT: '%s' HAS NO PARSER FOR NODE TYPE '%s'"
                    .formatted(dialect, nodeClass.getSimpleName()));
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
