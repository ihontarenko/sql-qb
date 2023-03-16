package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.platform.mysql.ast.OffsetNode;
import pro.javadev.sql.platform.ansi_sql.ast.SelectNode;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class SelectStatementParser implements Parser<SelectNode> {

    @Override
    public SelectNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        SelectNode node = new SelectNode();

        context.getParser(dialect, OffsetNode.class).parse(dialect, context, tokenizer);

        return node;
    }

}

