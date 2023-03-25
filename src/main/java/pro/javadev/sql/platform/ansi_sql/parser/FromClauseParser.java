package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.FromClause;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ExpressionRecognizer;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

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

