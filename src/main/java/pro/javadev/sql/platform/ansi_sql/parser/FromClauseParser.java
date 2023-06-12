package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.FromClause;
import pro.javadev.sql.internal.parser.AbstractParser;
import pro.javadev.sql.internal.parser.ExpressionRecognizer;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

import static pro.javadev.sql.platform.ansi_sql.SQLToken.T_SQL_FROM;

public class FromClauseParser extends AbstractParser<FromClause> {

    @Override
    public FromClause parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        FromClause node = new FromClause();

        return node;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_SQL_FROM);
    }

}

