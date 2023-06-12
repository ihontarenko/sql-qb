package pro.javadev.sql.platform.mysql;

import pro.javadev.sql.internal.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.AnsiExpressionRecognizer;

import static pro.javadev.sql.platform.mysql.MySQLToken.T_MYSQL_FIELD_PATH;

public class MySQLExpressionRecognizer extends AnsiExpressionRecognizer {

    @Override
    public boolean isFieldPathExpression(Tokenizer tokenizer) {
        return super.isFieldPathExpression(tokenizer) || tokenizer.isCurrent(T_MYSQL_FIELD_PATH);
    }

}
