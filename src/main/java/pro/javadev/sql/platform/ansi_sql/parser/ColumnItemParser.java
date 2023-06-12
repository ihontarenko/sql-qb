package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.ColumnItem;
import pro.javadev.sql.internal.ast.ExpressionItem;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.Parser;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public class ColumnItemParser extends AbstractParser<ColumnItem> {

    @Override
    public ColumnItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ColumnItem             columnItem = new ColumnItem();
        Parser<ExpressionItem> parser     = context.getParser(dialect, ExpressionItem.class);

        columnItem.add(parser.parse(dialect, context, tokenizer));

        return columnItem;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isExpression(tokenizer) || recognizer.isArithmeticExpression(tokenizer);
    }

}
