package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.ast.statement.FromNode;

public class FlomClauseParser implements Parser<FromNode> {

    @Override
    public FromNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        FromNode node = new FromNode();

        return node;
    }

}

