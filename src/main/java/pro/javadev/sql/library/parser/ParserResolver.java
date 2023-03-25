package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.tokenizer.Tokenizer;

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
