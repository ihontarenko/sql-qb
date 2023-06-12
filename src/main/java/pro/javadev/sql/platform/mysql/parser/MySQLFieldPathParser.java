package pro.javadev.sql.platform.mysql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.FieldPathExpression;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.parser.FieldPathParser;

import static pro.javadev.sql.platform.mysql.MySQLToken.T_MYSQL_FIELD_PATH;

public class MySQLFieldPathParser extends FieldPathParser {

    @Override
    public FieldPathExpression parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        return parse(getCurrentToken(T_MYSQL_FIELD_PATH, tokenizer), dialect);
    }

}
