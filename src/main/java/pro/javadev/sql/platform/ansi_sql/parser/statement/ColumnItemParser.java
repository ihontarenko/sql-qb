package pro.javadev.sql.platform.ansi_sql.parser.statement;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.statement.ColumnItem;
import pro.javadev.sql.library.ast.statement.FieldPathExpression;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ExpressionRecognizer;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class ColumnItemParser extends AbstractParser<ColumnItem> {

    @Override
    public ColumnItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ExpressionRecognizer recognizer = context.getExpressionRecognizer(dialect);
        ColumnItem           columnItem = new ColumnItem();

        if (recognizer.isFieldPathExpression(tokenizer)) {
            Parser<FieldPathExpression> parser = context.getParser(dialect, FieldPathExpression.class);
            columnItem.add(parser.parse(dialect, context, tokenizer));
        } else if (recognizer.isFunctionExpression(tokenizer)) {
            System.out.println("function");
        }

        return columnItem;
    }

}
