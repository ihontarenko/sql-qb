package pro.javadev.sql.internal.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ASTNode;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public class ParserResolver {

    private final Class<? extends ASTNode>[] classes;

    @SafeVarargs
    public ParserResolver(Class<? extends ASTNode>... classes) {
        this.classes = classes;
    }

    public Parser<? extends ASTNode> resolve(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        Parser<? extends ASTNode> parser     = null;
        var                       recognizer = context.getExpressionRecognizer(dialect);

        for (var nodeClass : classes) {
            var currentParser = context.getParser(dialect, nodeClass);
            if (currentParser.matchable(recognizer, tokenizer)) {
                parser = currentParser;
                break;
            }
        }

        return parser;
    }

}
