package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.*;
import pro.javadev.sql.library.parser.*;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class ExpressionItemParser extends AbstractParser<ExpressionItem> {

    @Override
    public ExpressionItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ExpressionItem            item       = new ExpressionItem();
        ExpressionRecognizer      recognizer = context.getExpressionRecognizer(dialect);
        Parser<? extends ASTNode> parser;

        Parser<? extends ASTNode> parser1 = chain(dialect, context,
                FieldPathExpression.class, ArithmeticExpression.class, FunctionCallExpression.class,
                IdentifierNode.class, LiteralNode.class, SelectStatement.class);

        if (recognizer.isFieldPathExpression(tokenizer)) {
            parser = context.getParser(dialect, FieldPathExpression.class);
        } else if (recognizer.isArithmeticExpression(tokenizer)) {
            parser = context.getParser(dialect, ArithmeticExpression.class);
        } else if (recognizer.isFunctionExpression(tokenizer)) {
            parser = context.getParser(dialect, FunctionCallExpression.class);
        }  else if (recognizer.isIdentifier(tokenizer)) {
            parser = context.getParser(dialect, IdentifierNode.class);
        } else if (recognizer.isLiteralExpression(tokenizer)) {
            parser = context.getParser(dialect, LiteralNode.class);
        } else {
            throw new ParserException("UNRECOGNIZABLE NEXT TOKEN" + tokenizer.current());
        }

        // chain(FieldPathExpression.class, ArithmeticExpression.class)

        item.add(parser.parse(dialect, context, tokenizer));

        return item;
    }

    @Override
    public boolean isApplicable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isExpression(tokenizer);
    }

}
