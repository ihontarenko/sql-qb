package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.platform.ansi_sql.ast.statement.SelectNode;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class SelectStatementParser implements Parser<SelectNode> {

    @Override
    public SelectNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        SelectNode node = new SelectNode();

        return node;
    }

}

