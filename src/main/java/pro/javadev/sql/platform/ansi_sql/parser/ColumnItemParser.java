package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ColumnItem;
import pro.javadev.sql.library.ast.ExpressionItem;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ExpressionRecognizer;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

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
