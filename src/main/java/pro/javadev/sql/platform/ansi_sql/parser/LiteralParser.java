package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.parser.Parser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.ast.LiteralNode;

public class LiteralParser implements Parser<LiteralNode> {

    @Override
    public LiteralNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        LiteralNode node = new LiteralNode();

        return node;
    }

}

