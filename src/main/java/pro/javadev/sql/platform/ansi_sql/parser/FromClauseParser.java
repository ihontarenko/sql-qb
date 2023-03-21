package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.library.ast.FromClause;

public class FromClauseParser implements Parser<FromClause> {

    @Override
    public FromClause parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        FromClause node = new FromClause();

        return node;
    }

}

