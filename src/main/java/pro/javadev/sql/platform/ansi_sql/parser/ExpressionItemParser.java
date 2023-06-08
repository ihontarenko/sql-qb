package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.*;
import pro.javadev.sql.library.parser.*;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class ExpressionItemParser extends AbstractParser<ExpressionItem> {

    @Override
    public ExpressionItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ExpressionItem item = new ExpressionItem();
        Parser<? extends ASTNode> parser = resolver(
                FieldPathExpression.class,
                ArithmeticExpression.class,
                FunctionCallExpression.class,
                IdentifierNode.class,
                LiteralNode.class
        ).resolve(dialect, context, tokenizer);

        if (parser == null) {
            throw new ParserException("UNRECOGNIZABLE NEXT TOKEN" + tokenizer.current());
        }

        item.add(parser.parse(dialect, context, tokenizer));

        return item;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isExpression(tokenizer);
    }

}
