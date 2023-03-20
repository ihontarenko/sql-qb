package pro.javadev.sql.platform.mysql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.statement.ColumnItem;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.parser.statement.ColumnItemParser;
import pro.javadev.sql.platform.mysql.MySQLToken;

public class MySQLColumnItemParser extends ColumnItemParser {

    @Override
    public ColumnItem parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        return parseFieldPathExpression(consumeToken(MySQLToken.T_MYSQL_FIELD_PATH, tokenizer), dialect);
    }

}
