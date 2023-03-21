package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ExpressionItem;
import pro.javadev.sql.library.ast.FieldPathExpression;
import pro.javadev.sql.library.ast.FunctionCallExpression;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ExpressionRecognizer;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class ExpressionItemParser extends AbstractParser<ExpressionItem> {

    @Override
    public ExpressionItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ExpressionItem       item       = new ExpressionItem();
        ExpressionRecognizer recognizer = context.getExpressionRecognizer(dialect);

        if (recognizer.isFieldPathExpression(tokenizer)) {
            Parser<FieldPathExpression> parser = context.getParser(dialect, FieldPathExpression.class);
            item.add(parser.parse(dialect, context, tokenizer));
        } else if (recognizer.isFunctionExpression(tokenizer)) {
            Parser<FunctionCallExpression> parser = context.getParser(dialect, FunctionCallExpression.class);
            item.add(parser.parse(dialect, context, tokenizer));
        } else if (recognizer.isIdentifier(tokenizer)) {
            Parser<IdentifierNode> parser = context.getParser(dialect, IdentifierNode.class);
            IdentifierNode         node   = parser.parse(dialect, context, tokenizer);
            item.add(new FieldIdentifier(node.getIdentifier()));
        }

        return item;
    }

}
