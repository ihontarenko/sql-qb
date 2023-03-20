package pro.javadev.sql.platform.ansi_sql.parser.statement;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.statement.ColumnItem;
import pro.javadev.sql.library.ast.statement.FieldPathExpression;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.token.DefaultToken;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import java.util.function.Predicate;

public class ColumnItemParser extends AbstractParser<ColumnItem> {

    @Override
    public ColumnItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        Predicate<Tokenizer> tester = context.getExpressionRecognizer(dialect)::isSubSelectExpression;

        return parseFieldPathExpression(consumeToken(DefaultToken.T_IDENTIFIER, tokenizer), dialect);
    }

    protected ColumnItem parseFieldPathExpression(Token.Entry entry, SQLDialect dialect) {
        String              path       = entry.value();
        String[]            parts      = path.split("\\.");
        String              table      = parts[0];
        String              field      = parts[1];
        FieldPathExpression expression = new FieldPathExpression();
        ColumnItem          item       = new ColumnItem();

        if (table.indexOf(dialect.getEscapeCharacter()) == 0) {
            table = table.substring(1, table.length() - 1);
        }

        if (field.indexOf(dialect.getEscapeCharacter()) == 0) {
            field = field.substring(1, field.length() - 1);
        }

        expression.setTable(table);
        expression.setField(field);

        item.add(expression);

        return item;
    }

}
