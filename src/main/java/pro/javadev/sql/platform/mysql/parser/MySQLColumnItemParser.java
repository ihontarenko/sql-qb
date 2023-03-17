package pro.javadev.sql.platform.mysql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.library.ast.statement.ColumnItem;
import pro.javadev.sql.library.ast.statement.FieldPathExpression;

import static pro.javadev.sql.platform.mysql.MySQLToken.T_MYSQL_FIELD_PATH;

public class MySQLColumnItemParser extends AbstractParser<ColumnItem> {

    @Override
    public ColumnItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        Token.Entry         entry      = consumeToken(T_MYSQL_FIELD_PATH, tokenizer);
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
