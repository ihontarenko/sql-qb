package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.AliasExpression;
import pro.javadev.sql.internal.ast.FieldPathExpression;
import pro.javadev.sql.internal.ast.TableIdentifier;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.token.Token;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

import static pro.javadev.sql.internal.token.DefaultToken.T_FIELD_PATH;

public class FieldPathParser extends AbstractParser<FieldPathExpression> {

    @Override
    public FieldPathExpression parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        return parse(getCurrentToken(T_FIELD_PATH, tokenizer), dialect);
    }

    protected FieldPathExpression parse(Token.Entry entry, SQLDialect dialect) {
        String              path       = entry.value();
        String[]            parts      = path.split("\\.");
        String              table      = parts[0];
        String              field      = parts[1];
        FieldPathExpression expression = new FieldPathExpression();

        if (table.indexOf(dialect.getOpenEscapeCharacter()) == 0) {
            table = table.substring(1, table.length() - 1);
        }

        if (field.indexOf(dialect.getOpenEscapeCharacter()) == 0) {
            field = field.substring(1, field.length() - 1);
        }

        expression.setTable(new TableIdentifier(table));
        expression.setField(new AliasExpression.FieldIdentifier(field));

        return expression;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isFieldPathExpression(tokenizer);
    }

}
