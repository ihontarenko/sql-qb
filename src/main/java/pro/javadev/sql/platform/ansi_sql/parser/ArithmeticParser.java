package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.*;
import pro.javadev.sql.internal.parser.*;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public class ArithmeticParser extends AbstractParser<ArithmeticExpression> {

    @Override
    public ArithmeticExpression parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ArithmeticExpression      expression = new ArithmeticExpression();
        ExpressionRecognizer      recognizer = context.getExpressionRecognizer(dialect);

//        Parser<? extends ASTNode> parser;
//
//        if (recognizer.isFieldPathExpression(tokenizer)) {
//            parser = context.getParser(dialect, FieldPathExpression.class);
//        } else if (recognizer.isFunctionExpression(tokenizer)) {
//            parser = context.getParser(dialect, FunctionCallExpression.class);
//        } else if (recognizer.isIdentifier(tokenizer)) {
//            parser = context.getParser(dialect, IdentifierNode.class);
//        } else if (recognizer.isLiteralExpression(tokenizer)) {
//            parser = context.getParser(dialect, LiteralNode.class);
//        } else {
//            throw new ParserException("UNRECOGNIZABLE NEXT TOKEN" + tokenizer.current());
//        }

        expression.add(context.getParser(dialect, ExpressionItem.class).parse(dialect, context, tokenizer));

        if (recognizer.isArithmeticOperator(tokenizer)) {
            expression.add(new ArithmeticOperator(tokenizer.next().value()));
            expression.add(context.getParser(dialect, ExpressionItem.class).parse(dialect, context, tokenizer));
        }

        return expression;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isArithmeticExpression(tokenizer);
    }

}
